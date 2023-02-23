package com.gupao.edu.marketing.client.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 优惠券优惠策略
 *
 * @author leon
 * @date 2020-04-06 14:57:19
 */
@Data
public class CouponPreferentialStrategyDto {
	private static final long serialVersionUID = 1L;

	/**
	 * 优惠券ID
	 */
	private Integer couponId;

	/**
	 * 满金额
	 */
	private Integer fullPrice;

	/**
	 * 减金额
	 */
	private Integer reducePrice;

	/**
	 * 打折
	 */
	private BigDecimal discount;

}
