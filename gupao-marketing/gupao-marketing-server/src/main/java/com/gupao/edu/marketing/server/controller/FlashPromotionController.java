package com.gupao.edu.marketing.server.controller;


import com.gupao.edu.common.result.Result;
import com.gupao.edu.marketing.client.dto.CourseFlashPromotionDto;
import com.gupao.edu.marketing.client.req.GetCourseFlashPromotionReq;
import com.gupao.edu.marketing.server.service.FlashPromotionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author leon
 * @since 2020-03-23
 */
@Api("限时优惠模块")
@RestController
@RequestMapping("/flash-promotion")
public class FlashPromotionController {

	private final FlashPromotionService flashPromotionService;

	public FlashPromotionController(FlashPromotionService flashPromotionService) {
		this.flashPromotionService = flashPromotionService;
	}

	/**
	 * 获取课程优惠活动
	 *
	 * @return Result<CourseFlashPromotionDto>
	 */
	@GetMapping("/getCourseFlashPromotion")
	public Result<CourseFlashPromotionDto> getCourseFlashPromotion(@Valid GetCourseFlashPromotionReq getCourseFlashPromotionReq) {
		return 	flashPromotionService.getCourseFlashPromotion(getCourseFlashPromotionReq);
	}

}
