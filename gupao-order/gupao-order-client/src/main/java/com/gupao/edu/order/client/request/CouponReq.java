package com.gupao.edu.order.client.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 优惠券dto
 *
 * @author leon
 * @date 2020-03-17 19:38:15
 */
@Data
public class CouponReq {
	/**
	 * 优惠券ID
	 */
	@ApiModelProperty(value="优惠券ID", name="id",example = "1", required = true)
	private Integer id;
	/**
	 * 优惠券名称
	 */
	@ApiModelProperty(value="优惠券名称", name="name",example = "jolin", required = true)
	private String name;
	/**
	 * 优惠券描述
	 */
	@ApiModelProperty(value="优惠券名称", name="description",example = "jolin", required = false)
	private String description;
	/**
	 * 优惠券开始使用时间
	 */
	@ApiModelProperty(value="优惠券开始使用时间", name="startTime",example = "jolin", required = false)
	private String startTime;
	/**
	 * 优惠券过期时间
	 */
	@ApiModelProperty(value="优惠券过期时间", name="endTime",example = "jolin", required = false)
	private String endTime;

}
