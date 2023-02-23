package com.gupao.edu.marketing.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.marketing.client.dto.CourseFlashPromotionDto;
import com.gupao.edu.marketing.client.req.GetCourseFlashPromotionReq;
import com.gupao.edu.marketing.client.entity.FlashPromotion;

/**
 * <p>
 * 限时优惠活动表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface FlashPromotionService extends IService<FlashPromotion> {

	/**
	 * 获取课程限时优惠活动
	 *
	 * @param  getCourseFlashPromotionReq getCourseFlashPromotionReq
	 * @return Result<CourseFlashPromotionDto>
	 */
	Result<CourseFlashPromotionDto> getCourseFlashPromotion(GetCourseFlashPromotionReq getCourseFlashPromotionReq);
}
