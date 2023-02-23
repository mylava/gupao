package com.gupao.edu.marketing.client.dto;

import lombok.Data;

/**
 * 课程限时优惠dto
 *
 * @author leon
 * @date 2020-03-23 22:22:46
 */
@Data
public class CourseFlashPromotionDto {
	/**
	 * 剩余时间
	 */
	private Long remainTime;
	/**
	 * 可用数量
	 */
	private Integer availableQuantity;
}
