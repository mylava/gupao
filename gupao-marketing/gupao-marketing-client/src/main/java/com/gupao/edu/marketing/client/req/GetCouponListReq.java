package com.gupao.edu.marketing.client.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 获取优惠券列表
 *
 * @author leon
 * @date 2020-03-17 20:24:22
 */
@Data
@ApiModel("获取优惠券列表req")
public class GetCouponListReq {
	/**
	 * 用户唯一编码
	 */
	@ApiModelProperty(value = "用户唯一编码",required = true)
	private String userUniqueCode;
//
//	@ApiModelProperty(value = "操作标识 0:全部 1:未领取 2:已经领取",required = true)
//	private Integer flag;
}
