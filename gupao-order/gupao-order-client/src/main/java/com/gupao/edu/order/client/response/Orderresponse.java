package com.gupao.edu.order.client.response;

import com.gupao.edu.order.client.entity.Orders;
import lombok.Data;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-04 16:55
 */
@Data
public class Orderresponse {

    /**
     * 订单信息
     */
    private Orders  orders;

    /**
     * 错误信息
     */
    private String message;
}
