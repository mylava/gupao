package com.gupao.edu.account.client.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Class: 优惠卷领取请求参数
 *
 * @Author: wangzhong
 * @Date: 2020-04-01 15:35
 */
@Data
@ApiModel
public class ReceiveCouponReq {
	@ApiModelProperty("优惠劵id")
	private Integer couponId;
	@ApiModelProperty("用户id")
	private Integer userId;
}