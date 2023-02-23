package com.gupao.edu.order.server.controller;

import com.gupao.edu.common.result.Result;

import com.gupao.edu.order.client.entity.OrderPay;
import com.gupao.edu.order.client.request.OrderPayReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhanXiao
 * @Description: 订单支付Controller
 * @date 2020/3/29 下午9:54
 */
@Api(value = "订单支付相关", tags = {"订单支付相关的api接口"})
@RestController
@RequestMapping("/order/pay")
public class OrderPayController {

    @ApiOperation(value = "1.立即支付", notes = "支付", httpMethod = "POST")
    @PostMapping("/doOrderPay")
    public Result< OrderPay > doOrderPay(@RequestBody OrderPayReq orderPayReq) {
        return null;
    }
}
