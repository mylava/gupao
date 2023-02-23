package com.gupao.edu.account.client.req;

import lombok.Data;

/**
 * Class:
 *
 * @Author: wangzhong
 * @Date: 2020-04-01 20:06
 */
@Data
public class UseCouponReq {
private  String orderId;
private Integer userId;
private Integer couponId;
}