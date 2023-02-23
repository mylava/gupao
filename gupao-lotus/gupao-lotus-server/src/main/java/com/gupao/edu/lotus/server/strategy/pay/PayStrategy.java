package com.gupao.edu.lotus.server.strategy.pay;

import com.gupao.edu.lotus.server.service.pay.PayService;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**支付策略
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-28 09:27
 */
@Component
public  class PayStrategy {
    /**
     * 支付策略容器
     */
    private static Map<String, PayService > payStrategy = new HashMap<>();


    /**
     * 支付策略容器添加支付方式
     * @param payKey
     * @param payService
     */
    public static void set(String payKey, PayService payService){
        payStrategy.put(payKey, payService);
    }

    /**
     * 获取当前容器对象
     * @param payKey
     * @return
     */
    public static PayService get(String payKey){
        return  payStrategy.get(payKey);
    }
}
