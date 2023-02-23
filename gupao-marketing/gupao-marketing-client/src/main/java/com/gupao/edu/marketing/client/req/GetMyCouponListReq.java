package com.gupao.edu.marketing.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取我的优惠券列表
 *
 * @author leon
 * @date 2020-04-20 22:58:12
 */
@Data
public class GetMyCouponListReq {
	/**
	 * 用户唯一编码
	 */
	@ApiModelProperty(value = "用户唯一编码",required = true)
	private String userUniqueCode;
//
//	@ApiModelProperty(value = "操作标识 1:已经使用 2:未使用", required = true)
//	private Integer flag;
}
