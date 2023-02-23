package com.gupao.edu.marketing.client.feign;

import com.gupao.edu.marketing.client.dto.CourseFlashPromotionDto;
import com.gupao.edu.marketing.client.req.GetCourseFlashPromotionReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 限时优惠券相关feign
 *
 * @author leon
 * @date 2020-05-07 13:0:50
 */
@FeignClient(value = "gupao-marketing", url = "http://localhost:18051/")
public interface FlashPromotionFeign {

	/**
	 * 获取课程限时优惠
	 *
	 * @param getCourseFlashPromotionReq getCourseFlashPromotionReq
	 * @return CourseFlashPromotionDto
	 */
	@GetMapping("/getCourseFlashPromotion")
	CourseFlashPromotionDto getCourseFlashPromotion(GetCourseFlashPromotionReq getCourseFlashPromotionReq);

}
