package com.gupao.edu.order.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.order.client.entity.ExpressBasicInfo;
import com.gupao.edu.order.client.entity.OrderLogistics;
import com.gupao.edu.order.client.vo.TransportInformationDataVO;
import com.gupao.edu.order.client.vo.TransportInformationVO;
import com.gupao.edu.order.server.mapper.OrderLogisticsMapper;
import com.gupao.edu.order.server.service.ExpressBasicInfoService;
import com.gupao.edu.order.server.service.OrderLogisticsService;
import com.gupao.edu.order.server.utils.logistics.LogisticsUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 物流信息表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsMapper, OrderLogistics> implements OrderLogisticsService {


    @Value("${transport.info.key}")
    private String key;
    @Value("${transport.info.customer}")
    private String customer;

    @Resource
    ExpressBasicInfoService expressBasicInfoService;
    @Resource
    OrderLogisticsMapper orderLogisticsMapper;

    @Override
    public TransportInformationVO queryLogistics(String orderNo, String userId) {
        QueryWrapper<OrderLogistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        queryWrapper.eq("user_id", userId);
        OrderLogistics orderLogistics = orderLogisticsMapper.selectOne(queryWrapper);
        if (orderLogistics == null) {
            throw  new  RuntimeException("未找到"+orderNo+"的物流信息");
        }
        //快递公司编号
        String trackCompanyCode = orderLogistics.getUserUniqueCode();
        String trackingNumber = orderLogistics.getLogisticsChannel();
        String queryData = LogisticsUtil.synQueryData(key, customer, trackCompanyCode, trackingNumber, "", "", "", 0);
        JSONObject jsonObject = JSONObject.parseObject(queryData);
        int status = jsonObject.getIntValue("status");
        //返回 200 说明查询成功
        if (HttpStatus.OK.value() == status) {
            TransportInformationVO tiv = new TransportInformationVO();
            int isCheck = jsonObject.getIntValue("ischeck");
            String com = jsonObject.getString("com");
            String num = jsonObject.getString("nu");
            tiv.setIscheck(isCheck);
            tiv.setCom(com);
            tiv.setNu(num);
            QueryWrapper< ExpressBasicInfo > wrapper = new QueryWrapper<>();
            wrapper.eq("company_code", num);
            ExpressBasicInfo basicInfo = expressBasicInfoService.getOne(wrapper);
            tiv.setCompanyName(basicInfo.getCompanyName());
            JSONArray data = jsonObject.getJSONArray("data");
            List< TransportInformationDataVO > dataList = new ArrayList<>(data.size());
            for (int i = 0; i < data.size(); i++) {
                TransportInformationDataVO dataVO = new TransportInformationDataVO();
                JSONObject object = data.getJSONObject(i);
                String context = object.getString("context");
                String time = object.getString("time");
                String formatTime = object.getString("ftime");
                dataVO.setContext(context);
                dataVO.setFtime(formatTime);
                dataVO.setTime(time);
                dataList.add(dataVO);
            }
            tiv.setDataVOList(dataList);
            return tiv;
        } else {
            return null;
        }
    }


}
