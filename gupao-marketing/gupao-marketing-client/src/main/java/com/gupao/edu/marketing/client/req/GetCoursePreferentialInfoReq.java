package com.gupao.edu.marketing.client.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取课程优惠信息
 *
 * @author leon
 * @date 2020-03-24 21:19:41
 */
@Data
@ApiModel("获取课程优惠信息请求参数")
public class GetCoursePreferentialInfoReq {
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID",required = true)
	private String userUniqueCode;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID",required = true)
	private Integer courseId;
	/**
	 * 课程价格
	 */
	@ApiModelProperty(value = "课程价格",required = true)
	private Integer coursePrize;
}
