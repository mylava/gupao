package com.gupao.edu.marketing.client.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 优惠券列表
 *
 * @author leon
 * @date 2020-04-18 00:03:39
 */
@Data
public class CouponListDto {
	/**
	 * 领取数量
	 */
	@ApiModelProperty("领取数量")
	private Integer receiveCount;

	/**
	 * 未领取数量
	 */
	@ApiModelProperty("未领取数量")
	private Integer unReceiveCount;

	/**
	 * 待领取优惠券列表
	 */
	@ApiModelProperty("未领取列表")
	private List<CouponDto> waitingReceiveCouponList;

	/**
	 * 已经领取优惠券列表
	 */
	@ApiModelProperty("已经领取优惠券列表")
	private List<CouponDto> receivedCouponList;


}
