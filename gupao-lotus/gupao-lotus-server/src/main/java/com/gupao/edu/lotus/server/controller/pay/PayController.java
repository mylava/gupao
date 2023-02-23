package com.gupao.edu.lotus.server.controller.pay;



import com.gupao.edu.common.result.Result;

import com.gupao.edu.lotus.server.enums.PayEnums;
import com.gupao.edu.lotus.server.service.pay.PayService;
import com.gupao.edu.lotus.server.strategy.pay.PayStrategy;
import com.gupao.edu.order.client.entity.OrderPay;
import com.gupao.edu.order.client.request.OrderPayReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @Description: 支付Controller
* @author ZhanXiao
* @date 2020/3/29 下午9:54
*/
@Api(value = "支付相关", tags = {"支付相关的api接口"})
@RestController
@RequestMapping("/pay")
public class PayController {


    @ApiOperation(value = "", notes = "支付", httpMethod = "POST")
    @PostMapping("/do")
    public Result< OrderPay > pay(@RequestBody OrderPayReq payReq) {
        long x = System.currentTimeMillis();
        String name = PayEnums.PayStrategy.valueOf1(payReq.getPayMethod()).getName();
        PayService payService = PayStrategy.get(name);
        OrderPay orderPay = payService.doPay(payReq);
        return null;
    }

    @ApiOperation(value = "", notes = "支付宝回调", httpMethod = "POST")
    @PostMapping("/ali/callback")
    public String aliCallabck(HttpServletRequest request, HttpServletResponse response) throws Exception {
       return PayStrategy.get(PayEnums.PayStrategy.ALI_PAY.getName()).callBack(request,response);
    }

    @ApiOperation(value = "", notes = "微信回调", httpMethod = "POST")
    @PostMapping("/wechat/callback")
    public String wechatCallabck(HttpServletRequest request, HttpServletResponse response) throws Exception {
       return PayStrategy.get(PayEnums.PayStrategy.ALI_PAY.getName()).callBack(request,response);
    }
}
