package com.gupao.edu.marketing.client.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取优惠券详情req
 *
 * @author leon
 * @date 2020-04-06 15:01:45
 */
@Data
@ApiModel("获取优惠券详情req")
public class GetCouponDetailReq {
	/**
	 * 优惠券ID
	 */
	@ApiModelProperty(value = "优惠券ID", required = true)
	private Integer couponId;
}
