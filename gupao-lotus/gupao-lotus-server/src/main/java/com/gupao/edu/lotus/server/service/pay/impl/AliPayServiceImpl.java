package com.gupao.edu.lotus.server.service.pay.impl;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.gupao.edu.lotus.server.enums.PayEnums;
import com.gupao.edu.lotus.server.resource.entity.AliPayResource;
import com.gupao.edu.lotus.server.service.pay.PayService;
import com.gupao.edu.lotus.server.strategy.pay.PayStrategy;
import com.gupao.edu.lotus.server.utils.payUtils.StrategyAliPayClient;
import com.gupao.edu.order.client.entity.OrderPay;
import com.gupao.edu.order.client.request.OrderPayReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/** 支付宝支付
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-28 09:41
 */
@Slf4j
@Service
public class AliPayServiceImpl  implements PayService {

    @Autowired
    private AliPayResource aliPayResource;


    @PostConstruct
    private void init() {
        log.info("这是AliPayServiceImpl 的init 方法");
        PayStrategy.set(PayEnums.PayStrategy.ALI_PAY.getName(), this);

    }

    @Override
    public OrderPay doPay(OrderPayReq payReq) {
        System.out.println("进入支付宝支付-appid:" + aliPayResource.getAppId());
        StrategyAliPayClient.doPay(aliPayResource, payReq);
        return null;
    }

    @Override
    public String callBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取支付宝POST过来反馈信息
        Map< String, String > params = new HashMap< String, String >();
        Map< String, String[] > requestParams = request.getParameterMap();
        for (Iterator< String > iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                aliPayResource.getPublicKey(),
                aliPayResource.getCharset(),
                aliPayResource.getSignType()); //调用SDK验证签名
        if (signVerified) {//验证成功
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_SUCCESS")) {
                return "success";
            }

        }
        return "fail";

    }

}
