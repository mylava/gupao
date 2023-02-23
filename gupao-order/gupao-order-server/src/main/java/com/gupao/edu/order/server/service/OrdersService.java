package com.gupao.edu.order.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.order.client.entity.Orders;
import com.gupao.edu.order.client.request.SubmitOrdersReq;
import com.gupao.edu.order.client.response.Orderresponse;
import com.gupao.edu.order.client.vo.OrderDetailsVO;
import com.gupao.edu.order.client.vo.OrderListVO;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface OrdersService extends IService<Orders> {


    /**
     * 创建支付订单
     * @param submitOrdersReq
     * @return
     */
    public Orderresponse createPayOrder(SubmitOrdersReq submitOrdersReq);

    /**
     * 第三方渠道订单创建
     * @param submitOrdersReq
     * @return
     */
    public Orders createThirdOrder(SubmitOrdersReq submitOrdersReq);

    /**
     * 查询订单列表
     * @param orderNo
     * @param userId
     * @return
     */
    public IPage< OrderListVO > queryOrderList(String orderNo, String userId, String orderStatus, Integer pageNum, Integer pageSize);

    /**
     *查询订单详情
     * @param orderNo
     * @param userId
     * @return
     */
    public   Result< OrderDetailsVO >  queryOrderDetails(String orderNo, String userId);


    /**
     * 删除订单
     * @param orderNo
     * @param userUniqueCode
     */
    public  void    deleteOrderInfo(String orderNo,String userUniqueCode);
}
