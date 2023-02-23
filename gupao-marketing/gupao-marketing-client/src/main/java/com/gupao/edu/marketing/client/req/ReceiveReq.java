package com.gupao.edu.marketing.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 领取优惠券参数
 *
 * @author leon
 * @date 2020-04-18 00:14:16
 */
@Data
public class ReceiveReq {
	/**
	 * 用户唯一编码
	 */
	@ApiModelProperty(value = "用户唯一编码", required = true)
	private String userUniqueCode;

	/**
	 * 优惠券ID
	 */
	@ApiModelProperty(value = "优惠券ID", required = true)
	private Integer couponId;
}
