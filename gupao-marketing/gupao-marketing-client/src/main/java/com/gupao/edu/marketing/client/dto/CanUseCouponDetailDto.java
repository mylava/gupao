package com.gupao.edu.marketing.client.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 可使用优惠券详情
 *
 * @author leon
 * @date 2020-05-09 14:02:59
 */
@Data
public class CanUseCouponDetailDto {
	/**
	 * 可用数量
	 */
	@ApiModelProperty("优惠卷列表可用数量")
	private Integer availableCount;
	/**
	 *
	 */
	@ApiModelProperty("优惠券信息列表")
	private List<CouponInfoDto> couponInfoDtoList;
}
