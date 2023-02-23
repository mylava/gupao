package com.gupao.edu.marketing.client.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 优惠券信息
 *
 * @author leon
 * @date 2020-05-09 13:53:54
 */
@Data
public class CouponInfoDto {
	/**
	 * 优惠券ID
	 */
	private Integer id;
	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 优惠券描述
	 */
	private String description;
	/**
	 * 优惠券开始使用时间
	 */
	private LocalDateTime startTime;
	/**
	 * 优惠券过期时间
	 */
	private LocalDateTime endTime;

	/**
	 * 优惠价格
	 */
	private Integer preferentialPrize;

	/**
	 * 优惠券优惠策略
	 */
	private CouponPreferentialStrategyDto couponPreferentialStrategyDto;
}
