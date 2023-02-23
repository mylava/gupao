package com.gupao.edu.marketing.client.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取可用优惠券列表
 *
 * @author leon
 * @date 2020-03-17 20:32:50
 */
@Data
@ApiModel("获取可用优惠券列表req")
public class GetCanUseCouponListReq {
	@ApiModelProperty(value = "用户唯一编码",required = true)
	private String userUniqueCode;
	@ApiModelProperty(value ="订单金额",required = true)
	private Integer orderAmount;
	@ApiModelProperty(value = "课程Id",required = true)
	private Integer courseId;
}
