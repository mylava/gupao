package com.gupao.edu.order.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.order.client.entity.OrderLogistics;
import com.gupao.edu.order.client.vo.TransportInformationVO;

/**
 * <p>
 * 物流信息表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface OrderLogisticsService extends IService<OrderLogistics> {


    /**
     * @Decsription: 查询物流信息
     * @Param orderNo 订单号
     * @Param userId 人员 ID
     * @Author: <a href="568227120@qq.com">heliang.wang</a>
     * @Date: 2020/4/3 7:29 下午
     * @return: com.gupao.edu.common.result.Result<com.gupao.edu.order.client.vo.TransportInformationVO>
     */
    TransportInformationVO queryLogistics(String orderNo, String userId);
}
