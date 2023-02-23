package com.gupao.edu.lotus.server.service.pay;

import com.alipay.api.AlipayApiException;
import com.gupao.edu.lotus.server.resource.entity.AliPayResource;
import com.gupao.edu.order.client.entity.OrderPay;
import com.gupao.edu.order.client.request.OrderPayReq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**支付接口
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-28 09:39
 */
public interface PayService {

    /**
     * 支付
     * @param payReq
     * @return
     */
    OrderPay doPay(OrderPayReq payReq);

    /**
     * 支付回调
     * @param request
     * @param response
     * @return
     */
    String callBack(HttpServletRequest request, HttpServletResponse response) throws  Exception;
}
