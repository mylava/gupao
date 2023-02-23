package com.gupao.edu.marketing.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.marketing.client.dto.*;
import com.gupao.edu.marketing.client.entity.Coupon;
import com.gupao.edu.marketing.client.req.*;

import java.util.List;

/**
 * <p>
 * 优惠券 服务类
 * </p>
 *
 * @author leon
 * @since 2020-03-23
 */
public interface CouponService extends IService<Coupon> {

	/**
	 * 获取待领取优惠券列表
	 *
	 * @param getCouponListReq getCouponListReq
	 * @return Result<List < CouponDto>>
	 */
	Result<List<CouponDto>> getWaitingReceiveCouponList(GetCouponListReq getCouponListReq);

	/**
	 * 获取已经领取 优惠券列表
	 *
	 * @param getCouponListReq getCouponListReq
	 * @return Result<List < CouponDto>
	 */
	Result<List<CouponDto>> getAlreadyReceivedCouponList(GetCouponListReq getCouponListReq);

	/**
	 * 获取优惠券列表
	 *
	 * @param getCouponListReq getCouponListReq
	 * @return Result<CouponListDto>
	 */
	Result<CouponListDto> getCouponList(GetCouponListReq getCouponListReq);


	/**
	 * 获取优惠券详情
	 *
	 * @param getCouponDetailReq getCouponDetailReq
	 * @return Result<CouponDetailDto>
	 */
	Result<CouponDetailDto> getCouponDetail(GetCouponDetailReq getCouponDetailReq);

	/**
	 * 获取课程优惠描述
	 *
	 * @param getCoursePreferentialInfoReq getCoursePreferentialInfoReq
	 * @return Result<CoursePreferentialInfoDto>
	 */
	Result<CoursePreferentialInfoDto> getCoursePreferentialInfo(GetCoursePreferentialInfoReq getCoursePreferentialInfoReq);

	/**
	 * 获取可用优惠券列表
	 *
	 * @param getCanUseCouponListReq getCanUseCouponListReq
	 * @return Result<List < CouponDetailDto>>
	 */
	Result<CanUseCouponDetailDto> getCanUseCouponDetail(GetCanUseCouponListReq getCanUseCouponListReq);

	/**
	 * 获取我的优惠券列表
	 *
	 * @param getMyCouponListReq getMyCouponListReq
	 * @return Result<MyCouponListDto>
	 */
	Result<MyCouponListDto> getMyCouponList(GetMyCouponListReq getMyCouponListReq);

	/**
	 * 领取优惠券
	 * @param receiveReq receiveReq
	 * @return Result<Object>
	 */
	Result<Object> receive(ReceiveReq receiveReq);
}
