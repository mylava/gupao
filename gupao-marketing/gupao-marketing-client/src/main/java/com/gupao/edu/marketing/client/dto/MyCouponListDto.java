package com.gupao.edu.marketing.client.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 我的优惠券列表dto
 *
 * @author leon
 * @date 2020-04-20 22:50:54
 */
@Data
public class MyCouponListDto {
	/**
	 * 领取数量
	 */
	@ApiModelProperty("使用数量")
	private Integer usedCount;

	/**
	 * 未领取数量
	 */
	@ApiModelProperty("未使用数量")
	private Integer unUsedCount;

	/**
	 * 待领取优惠券列表
	 */
	@ApiModelProperty("已经使用列表")
	private List<CouponDto> usedCouponList;

	/**
	 * 已经领取优惠券列表
	 */
	@ApiModelProperty("未使用优惠券列表")
	private List<CouponDto> unUsedCouponList;
}
