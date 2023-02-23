package com.gupao.edu.marketing.server.controller;

import com.gupao.edu.common.result.Result;
import com.gupao.edu.marketing.client.dto.*;
import com.gupao.edu.marketing.client.req.*;
import com.gupao.edu.marketing.server.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 优惠券
 *
 * @author leon
 * @date 2020-03-17 14:38:18
 */
@RestController
@Api("优惠券中心")
@RequestMapping("/coupon")
public class CouponController {

	private final CouponService couponService;

	public CouponController(CouponService couponService) {
		this.couponService = couponService;
	}

	/**
	 * 获取待领取优惠券列表(仅显示公开发行的优惠券列表)
	 *
	 * @param getCouponListReq getCouponListReq
	 * @return Result
	 */
	@GetMapping("/getWaitingReceiveCouponList")
	@ApiOperation("领劵中心-获取待领取优惠券列表")
	public Result<List<CouponDto>> getWaitingReceiveCouponList(@Valid GetCouponListReq getCouponListReq) {
		return couponService.getWaitingReceiveCouponList(getCouponListReq);
	}

	/**
	 * 获取优惠券列表(仅显示公开发行的优惠券列表)
	 *
	 * @param getCouponListReq getCouponListReq
	 * @return Result
	 */
	@GetMapping("/getAlreadyReceivedCouponList")
	@ApiOperation("领劵中心-获取已经领取优惠券列表")
	public Result<List<CouponDto>> getAlreadyReceivedCouponList(@Valid GetCouponListReq getCouponListReq) {
		return couponService.getAlreadyReceivedCouponList(getCouponListReq);
	}

	/**
	 * 获取优惠券列表 未领取 领取数量
	 *
	 * @param getCouponListReq getCouponListReq
	 * @return Result
	 */
	@GetMapping("/getCouponListCount")
	@ApiOperation("领劵中心顶部数字显示 未领取 领取数量")
	public Result<CouponListCount> getCouponListCount(GetCouponListReq getCouponListReq) {
		return null;
	}

	/**
	 * 获取优惠券列表
	 *
	 * @param getCouponListReq getCouponListReq
	 * @return Result<CouponListDto>
	 */
	@GetMapping("/list")
	@ApiOperation("获取优惠券列表")
	public Result<CouponListDto> getCouponList(@Valid GetCouponListReq getCouponListReq) {
		return couponService.getCouponList(getCouponListReq);
	}

	/**
	 * 我的优惠券列表
	 *
	 * @param getMyCouponListReq getMyCouponListReq
	 * @return Result<MyCouponListDto>
	 */
	@GetMapping("/myList")
	@ApiOperation("获取我的优惠券列表")
	public Result<MyCouponListDto> getMyCouponList(@Valid GetMyCouponListReq getMyCouponListReq) {
		return couponService.getMyCouponList(getMyCouponListReq);
	}


	/**
	 * 获取优惠券详情
	 *
	 * @param getCouponDetailReq getCouponDetailReq
	 * @return Result<CouponDetailDto>
	 */
	@GetMapping("/getCouponDetail")
	@ApiOperation("获取优惠券详情")
	public Result<CouponDetailDto> getCouponDetail(GetCouponDetailReq getCouponDetailReq) {
		return couponService.getCouponDetail(getCouponDetailReq);
	}

	/**
	 * 获取课程优惠信息
	 *
	 * @param getCoursePreferentialInfoReq getCoursePreferentialInfoReq
	 * @return Result
	 */
	@GetMapping("/getCoursePreferentialInfo")
	@ApiOperation("获取课程优惠信息")
	public Result<CoursePreferentialInfoDto> getCoursePreferentialInfo(GetCoursePreferentialInfoReq getCoursePreferentialInfoReq) {
		return couponService.getCoursePreferentialInfo(getCoursePreferentialInfoReq);
	}


	/**
	 * 获取可使用优惠券列表(下单时候使用)
	 *
	 * @param getCanUseCouponListReq getCanUseCouponListReq
	 * @return Result
	 */
	@GetMapping("/getCanUseCouponDetail")
	public Result<CanUseCouponDetailDto> getCanUseCouponDetail(GetCanUseCouponListReq getCanUseCouponListReq) {
		return couponService.getCanUseCouponDetail(getCanUseCouponListReq);
	}

	/**
	 * 领取接口
	 *
	 * @param receiveReq receiveReq
	 * @return Object
	 */
	@GetMapping("/receive")
	@ApiOperation("领取优惠券")
	public Result<Object> receive(@Valid ReceiveReq receiveReq) {
		return couponService.receive(receiveReq);
	}
}
