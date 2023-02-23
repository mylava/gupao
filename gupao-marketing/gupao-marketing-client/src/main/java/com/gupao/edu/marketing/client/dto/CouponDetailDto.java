package com.gupao.edu.marketing.client.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 优惠券详情
 *
 * @author leon
 * @date 2020-04-06 15:00:31
 */
@Data
public class CouponDetailDto {
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
	 * 优惠方式 1.打折 2. 满减3:立减
	 */
	private Integer preferentialWay;
	/**
	 * 优惠券开始使用时间
	 */
	private LocalDateTime startTime;
	/**
	 * 优惠券过期时间
	 */
	private LocalDateTime endTime;

	/**
	 * 优惠券优惠策略
	 */
	private CouponPreferentialStrategyDto couponPreferentialStrategyDto;
}
