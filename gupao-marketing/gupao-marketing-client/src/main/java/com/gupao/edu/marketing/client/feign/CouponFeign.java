package com.gupao.edu.marketing.client.feign;

import com.gupao.edu.marketing.client.dto.CanUseCouponDetailDto;
import com.gupao.edu.marketing.client.dto.CouponDetailDto;
import com.gupao.edu.marketing.client.dto.CoursePreferentialInfoDto;
import com.gupao.edu.marketing.client.req.GetCanUseCouponListReq;
import com.gupao.edu.marketing.client.req.GetCouponDetailReq;
import com.gupao.edu.marketing.client.req.GetCoursePreferentialInfoReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 优惠券相关feign
 *
 * @author leon
 * @date 2020-05-07 13:06:50
 */
@FeignClient(value = "gupao-marketing", url = "http://localhost:18051/")
public interface CouponFeign {

	/**
	 * 获取优惠券课程优惠详情
	 *
	 * @param getCoursePreferentialInfoReq getCoursePreferentialInfoReq
	 * @return CoursePreferentialInfoDto
	 */
	@GetMapping("/getCoursePreferentialInfo")
	CoursePreferentialInfoDto getCoursePreferentialInfo(GetCoursePreferentialInfoReq getCoursePreferentialInfoReq);

	/**
	 * 获取优惠券详情
	 *
	 * @param getCouponDetailReq getCouponDetailReq
	 * @return CouponDetailDto
	 */
	@GetMapping("/getCouponDetail")
	CouponDetailDto getCouponDetail(GetCouponDetailReq getCouponDetailReq);

	/**
	 * 获取可使用优惠券列表(下单时候使用)
	 *
	 * @param getCanUseCouponListReq getCanUseCouponListReq
	 * @return Result
	 */
	@GetMapping("/getCanUseCouponDetail")
	CanUseCouponDetailDto getCanUseCouponDetail(GetCanUseCouponListReq getCanUseCouponListReq);



}
