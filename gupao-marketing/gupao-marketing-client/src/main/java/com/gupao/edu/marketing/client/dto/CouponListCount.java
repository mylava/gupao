package com.gupao.edu.marketing.client.dto;

import lombok.Data;

/**
 * 优惠券列表数量
 *
 * @author leon
 * @date 2020-03-17 20:38:43
 */

@Data
public class CouponListCount {
	/**
	 * 领取数量
	 */
	private Integer receiveCount;
	/**
	 * 未领取数量
	 */
	private Integer unReceiveCount;
}
