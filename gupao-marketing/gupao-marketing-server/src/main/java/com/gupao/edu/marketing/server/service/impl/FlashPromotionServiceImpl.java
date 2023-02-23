package com.gupao.edu.marketing.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.marketing.client.dto.CourseFlashPromotionDto;
import com.gupao.edu.marketing.client.entity.FlashPromotion;
import com.gupao.edu.marketing.client.req.GetCourseFlashPromotionReq;
import com.gupao.edu.marketing.server.mapper.FlashPromotionMapper;
import com.gupao.edu.marketing.server.service.FlashPromotionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leon
 * @since 2020-03-23
 */
@Service
public class FlashPromotionServiceImpl extends ServiceImpl<FlashPromotionMapper, FlashPromotion> implements FlashPromotionService {

	@Override
	public Result<CourseFlashPromotionDto> getCourseFlashPromotion(GetCourseFlashPromotionReq getCourseFlashPromotionReq) {
		Integer courseId = getCourseFlashPromotionReq.getCourseId();
		CourseFlashPromotionDto courseFlashPromotionDto = new CourseFlashPromotionDto();
		//当前商品ID 为课程ID 扩展字段 后续可能为其他的具体的商品 或其他
		//状态为进行中
		List<FlashPromotion> flashPromotionList = this.getBaseMapper().selectList(new QueryWrapper<FlashPromotion>()
				.eq("goods_id", courseId)
				.eq("status","1"));
		if (CollectionUtils.isNotEmpty(flashPromotionList)) {
			Optional<FlashPromotion> first = flashPromotionList.stream().findFirst();
			if (first.isPresent()) {
				FlashPromotion flashPromotion = first.get();
				//可用数量
				Integer availableQuantity = flashPromotion.getAvailableQuantity();
				courseFlashPromotionDto.setAvailableQuantity(availableQuantity);
				//计算距离活动结束剩余时间
				LocalDateTime endTime = flashPromotion.getEndTime();
				long endTimeMillis = endTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
				Long remainTime = endTimeMillis - System.currentTimeMillis();
				courseFlashPromotionDto.setRemainTime(remainTime);
			}
		}
		return Result.success(courseFlashPromotionDto);
	}
}