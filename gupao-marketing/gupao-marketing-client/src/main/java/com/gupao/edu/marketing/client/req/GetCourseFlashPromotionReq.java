package com.gupao.edu.marketing.client.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author leon
 * @date 2020-03-23 22:27:28
 */
@Data
@ApiModel("获取课程限时活动请求参数")
public class GetCourseFlashPromotionReq {
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID",required = true)
	private Integer courseId;
}
