package com.gupao.edu.order.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;

import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;


import com.gupao.edu.common.utils.IdLeaf.impl.SnowflakeIdLeafServiceImpl;
import com.gupao.edu.lotus.client.facade.lotus.Payfacade;
import com.gupao.edu.order.client.entity.Orders;
import com.gupao.edu.order.client.request.SubmitOrdersReq;
import com.gupao.edu.order.client.response.Orderresponse;
import com.gupao.edu.order.client.vo.OrderDetailsVO;
import com.gupao.edu.order.client.vo.OrderListVO;
import com.gupao.edu.order.server.enums.OrdersEnums;
import com.gupao.edu.order.server.enums.PaymentEnums;
import com.gupao.edu.order.server.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author lipengfei
 * @since 2020-03-22
 */
@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RestController
@RequestMapping("orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private SnowflakeIdLeafServiceImpl snowflakeIdLeafService;

    @Autowired
    private Payfacade payfacade;


    /**
     * 创建订单
     *
     * @param submitOrdersReq
     * @return
     */
    @ApiOperation(value = "1.创建订单", notes = "创建订单", httpMethod = "POST")
    @ApiOperationSupport(order = 1)
//    @ApiImplicitParam(name = "submitOrdersReq", value = "创建订单请求实体", required = true, dataType = "SubmitOrdersReq")
    @PostMapping("/createOrder")
//    @KafkaListener(topics = "test",id = "")
    public Result< Orders > createOrder(@RequestBody SubmitOrdersReq submitOrdersReq) {   //Acknowledgment ack
        if (PaymentEnums.PayMethod.valueOf(submitOrdersReq.getPayMethod()) == null) {
            return Result.fail(CodeMessage.ORDER_NOT_ERROR.fillArgs("payMethod",submitOrdersReq.getPayMethod()));
        }

//        if (OrdersEnums.OrderType.valueOf(submitOrdersReq.getOrderType())==null) {
//            return Result.fail(CodeMessage.ORDER_NOT_ERROR);
//        }

        if (OrdersEnums.OrderClientChannel.valueOf1(submitOrdersReq.getClientSource()) == null) {
            return Result.fail(CodeMessage.ORDER_NOT_ERROR.fillArgs("clientSource",submitOrdersReq.getClientSource()));
        }
        if (OrdersEnums.OrderClientChannel.NETEASE_CLOUD_CLASSROOM.getValue().equals(submitOrdersReq.getClientSource()) ||
                OrdersEnums.OrderClientChannel.TENCENT_CLASSROOM.getValue().equals(submitOrdersReq.getClientSource())){
            //创建三方渠道订单
            Orders thirdOrder = ordersService.createThirdOrder(submitOrdersReq);
            return Result.success(thirdOrder);
        }else {
            //创建支付订单
            Orderresponse payOrder = ordersService.createPayOrder(submitOrdersReq);
            if (StringUtils.isNotBlank(payOrder.getMessage()))
                return Result.fail(CodeMessage.ORDER_NOT_USED_COUPON.getCode(),payOrder.getMessage());
            //        ack.acknowledge();//提交offset
            //TODO 发起支付及处理异常
            return Result.success(payOrder.getOrders());
        }

    }


    /**
     * 查询订单列表
     *
     * @return
     */
    @ApiOperation(value = "2.查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @ApiOperationSupport(order = 2)
    @PostMapping("/queryOrderList")
    public Result< IPage< OrderListVO > > queryOrderList(@ApiParam(name = "orderNo", value = "订单号", required = true) String orderNo,
                                                         @ApiParam(name = "userUniqueCode", value = "用户唯一编码", required = true) String userUniqueCode,
                                                         @ApiParam(name = "orderStatus", value = "订单状态", required = true) String orderStatus,
                                                         @ApiParam(name = "pageNum", value = "当前页", required = true) Integer pageNum,
                                                         @ApiParam(name = "pageSize", value = "当前页大小", required = true) Integer pageSize) {
        IPage< OrderListVO > orderListVOIPage = ordersService.queryOrderList(orderNo, userUniqueCode, orderStatus, pageNum, pageSize);
        return Result.success(orderListVOIPage);
    }

    /**
     * 查询订单详情
     *
     * @param orderNo
     * @param userUniqueCode
     * @return
     */
    @ApiOperation(value = "3.查询订单详情", notes = "查询订单详情", httpMethod = "POST")
    @PostMapping("/queryOrderDetails")
    @ApiOperationSupport(order = 3)
    public Result< OrderDetailsVO > queryOrderDetails(
            @ApiParam(name = "orderNo", value = "订单编号", required = true) String orderNo,
            @ApiParam(name = "userUniqueCode", value = "用户唯一编码", required = true) String userUniqueCode) {

        return ordersService.queryOrderDetails(orderNo, userUniqueCode);
    }


    @ApiOperation(value = "4.删除订单", notes = "删除订单", httpMethod = "POST")
    @PostMapping("/deleteOrderInfo")
    @ApiOperationSupport(order = 4)
    public void deleteOrderInfo(@ApiParam(name = "orderNo", value = "订单编号", required = true) String orderNo,
                                @ApiParam(name = "userUniqueCode", value = "用户唯一编码", required = true) String userUniqueCode) {

        ordersService.deleteOrderInfo(orderNo, userUniqueCode);

    }


}
