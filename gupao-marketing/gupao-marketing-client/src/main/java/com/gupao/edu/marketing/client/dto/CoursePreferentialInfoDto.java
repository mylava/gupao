package com.gupao.edu.marketing.client.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程优惠描述
 *
 * @author leon
 * @date 2020-03-24 21:24:15
 */
@Data
@Accessors(chain = true)
public class CoursePreferentialInfoDto {
	/**
	 * 优惠描述
	 */
	private String preferentialDesc;
}
