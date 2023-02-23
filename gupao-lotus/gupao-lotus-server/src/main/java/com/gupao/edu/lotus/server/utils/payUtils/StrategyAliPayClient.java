package com.gupao.edu.lotus.server.utils.payUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.gupao.edu.lotus.server.resource.entity.AliPayResource;
import com.gupao.edu.order.client.request.OrderPayReq;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-02 11:27
 */
@Slf4j
public class StrategyAliPayClient {
    /**
     * 阿里支付
     * @param aliPayResource
     * @param payReq
     */
    public static void doPay(AliPayResource aliPayResource, OrderPayReq payReq) {
        AlipayClient aliPayClient = new DefaultAlipayClient(aliPayResource.getServiceUrl(), aliPayResource.getAppId(), aliPayResource.getPrivateKey(), "json", aliPayResource.getCharset(), aliPayResource.getPublicKey(), aliPayResource.getSignType());
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(payReq.getOrderNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("GP_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(aliPayResource.getNotifyUrl());
        try {
            AlipayTradeAppPayResponse response = aliPayClient.sdkExecute(request);
            String body = response.getBody();
            System.out.println("body:"+body);
            System.out.println("支付结果："+response.isSuccess());
        } catch (AlipayApiException e) {
        }
    }
}
