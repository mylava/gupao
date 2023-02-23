package com.gupao.edu.lotus.server.service.pay.impl;

import com.alipay.api.AlipayApiException;
import com.gupao.edu.common.utils.XmlUtil;
import com.gupao.edu.lotus.server.enums.PayEnums;
import com.gupao.edu.lotus.server.resource.entity.WxPayResource;
import com.gupao.edu.lotus.server.resource.res.WxPayResult;
import com.gupao.edu.lotus.server.service.pay.PayService;
import com.gupao.edu.lotus.server.strategy.pay.PayStrategy;
import com.gupao.edu.lotus.server.utils.payUtils.StrategyWxPayClient;
import com.gupao.edu.order.client.entity.OrderPay;
import com.gupao.edu.order.client.request.OrderPayReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-02 13:32
 */
@Slf4j
@Service
public class WxPayServiceImpl implements PayService {


    @Autowired
    private WxPayResource wxPayResource;
    @PostConstruct
    private void init() {
        log.info("这是 WxPayServiceImpl 的init 方法");
        PayStrategy.set(PayEnums.PayStrategy.WX_PAY.getName(),this);
    }
    @Override
    public OrderPay doPay(OrderPayReq payReq) {

        try {
            StrategyWxPayClient.doPay(wxPayResource,payReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String callBack(HttpServletRequest request, HttpServletResponse response) throws  Exception {
        BufferedReader in = null;
        String result = "";
        in = new BufferedReader(
                new InputStreamReader(request.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        WxPayResult wxPayResult = (WxPayResult) XmlUtil.xml2Object(result, WxPayResult.class);
//		System.out.println(pr.toString());

        return   wxPayResult.getResult_code();
    }
}
