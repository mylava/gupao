package com.gupao.edu.lotus.server.utils.payUtils;

import com.gupao.edu.common.utils.HttpUtil;
import com.gupao.edu.common.utils.JsonUtils;
import com.gupao.edu.common.utils.XmlUtil;
import com.gupao.edu.lotus.server.resource.req.WxParamReq;
import com.gupao.edu.lotus.server.resource.entity.WxPayResource;
import com.gupao.edu.lotus.server.resource.res.WxPayRes;
import com.gupao.edu.lotus.server.utils.Sign;
import com.gupao.edu.order.client.request.OrderPayReq;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-02 13:40
 */
@Slf4j
public class StrategyWxPayClient {
    public static WxPayRes doPay(WxPayResource wxPayResource, OrderPayReq payReq) throws Exception {

        WxParamReq wxParamReq = new WxParamReq();
        // 生成随机字符串
        String nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
        wxParamReq.setAppid(wxPayResource.getAppId());
        wxParamReq.setBody("咕泡测试");
        wxParamReq.setMch_id(wxPayResource.getMerchantId());
        wxParamReq.setNotify_url(wxPayResource.getNotifyUrl());
        wxParamReq.setOut_trade_no(payReq.getOrderNo());

        // 判断有没有输入订单总金额，没有输入默认1分钱
        if (payReq.getAmount() != null && !payReq.getAmount() .equals("")) {
            wxParamReq.setTotal_fee(payReq.getAmount());
        } else {
            wxParamReq.setTotal_fee(1);
        }
        wxParamReq.setNonce_str(nonce_str);
        wxParamReq.setTrade_type(wxPayResource.getTradeType());
        wxParamReq.setSpbill_create_ip(wxPayResource.getSpbillCreateIp());


        SortedMap<Object, Object> p = new TreeMap<Object, Object>();
        p.put("appid", wxPayResource.getAppId());
        p.put("mch_id", wxPayResource.getMerchantId());
        p.put("body", "咕泡测试");
        p.put("nonce_str", nonce_str);
        p.put("out_trade_no", payReq.getOrderNo());
        p.put("total_fee", wxParamReq.getTotal_fee());
        p.put("spbill_create_ip", wxPayResource.getSpbillCreateIp());
        p.put("notify_url", wxPayResource.getNotifyUrl());
        p.put("trade_type", wxPayResource.getTradeType());
        // 获得签名
        String sign = Sign.createSign("utf-8", p, wxPayResource.getSecrectKey());
        wxParamReq.setSign(sign);
        // Object转换为XML
        String xml = XmlUtil.object2Xml(wxParamReq, WxParamReq.class);

        // 微信发起支付
        String returnXml = HttpUtil.sendPost(wxPayResource.getServiceUrl(), xml);
        //xml转化为map
        Map< String, String > stringStringMap = XmlUtil.xmlToMap(returnXml);
        // XML转换为Object
        WxPayRes wxPayRes = (WxPayRes) XmlUtil.xml2Object(returnXml, WxPayRes.class);
        System.out.println(JsonUtils.obj2String(wxPayRes));
        return wxPayRes;


    }


}
