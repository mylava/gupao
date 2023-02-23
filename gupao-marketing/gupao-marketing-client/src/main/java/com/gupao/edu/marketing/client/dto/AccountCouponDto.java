package com.gupao.edu.marketing.client.dto;

import lombok.Data;

import java.util.Date;

/**
 * 账户优惠券
 * @author leon
 * @date 2020-04-06 16:12:41
 */
@Data
public class AccountCouponDto {
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
	private Date startTime;
	/**
	 * 优惠券过期时间
	 */
	private Date endTime;

	/**
	 * 优惠卷使用情况 1-已使用 0-未使用
	 */
	private Integer useStatus;
}
