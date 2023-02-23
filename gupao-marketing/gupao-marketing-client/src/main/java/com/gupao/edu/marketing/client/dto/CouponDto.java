package com.gupao.edu.marketing.client.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 优惠券dto
 *
 * @author leon
 * @date 2020-03-17 19:38:15
 */
@Data
@Accessors(chain = true)
public class CouponDto {
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
	 * 领取状态 默认为0 未领取 1 已经领取
	 */
	private Integer status;

	/**
	 * 优惠卷使用情况 0-已使用 1-未使用 3-已过期
	 */
	private Integer useStatus;

}
