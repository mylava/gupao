package com.gupao.edu.marketing.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.marketing.client.dto.*;
import com.gupao.edu.marketing.client.entity.Coupon;
import com.gupao.edu.marketing.client.entity.CouponGoodsRelation;
import com.gupao.edu.marketing.client.entity.CouponPreferentialStrategy;
import com.gupao.edu.marketing.client.entity.UserCoupon;
import com.gupao.edu.marketing.client.req.*;
import com.gupao.edu.marketing.server.constant.CouponConstant;
import com.gupao.edu.marketing.server.mapper.CouponGoodsRelationMapper;
import com.gupao.edu.marketing.server.mapper.CouponMapper;
import com.gupao.edu.marketing.server.mapper.CouponPreferentialStrategyMapper;
import com.gupao.edu.marketing.server.mapper.UserCouponMapper;
import com.gupao.edu.marketing.server.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠券 服务实现类
 * </p>
 *
 * @author leon
 * @since 2020-03-23
 */
@Service
@Slf4j
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

	private final CouponPreferentialStrategyMapper couponPreferentialStrategyMapper;
	private final CouponGoodsRelationMapper couponGoodsRelationMapper;
	private final UserCouponMapper userCouponMapper;

	public CouponServiceImpl(CouponPreferentialStrategyMapper couponPreferentialStrategyMapper, CouponGoodsRelationMapper couponGoodsRelationMapper, UserCouponMapper userCouponMapper) {
		this.couponPreferentialStrategyMapper = couponPreferentialStrategyMapper;
		this.couponGoodsRelationMapper = couponGoodsRelationMapper;
		this.userCouponMapper = userCouponMapper;
	}

	/**
	 * 复制属性 到一个新的给定的对象模型
	 *
	 * @param source   source
	 * @param newModel newModel
	 * @param <T>      newModel
	 * @return T
	 */
	public static <T> T copyPropertiesToNewModel(Object source, Class<T> newModel) {
		T t;
		try {
			if (newModel != null) {
				t = newModel.newInstance();
				if (source != null) {
					BeanUtils.copyProperties(source, t);
					return t;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Result<List<CouponDto>> getWaitingReceiveCouponList(GetCouponListReq getCouponListReq) {
		String userUniqueCode = getCouponListReq.getUserUniqueCode();
		List<CouponDto> couponDtoList = getUnReceiveCouponDtoList(userUniqueCode);
		return Result.success(couponDtoList);
	}

	/**
	 * 获取用户未领取的优惠券列表
	 *
	 * @param userUniqueCode userUniqueCode
	 * @return List<CouponDto>
	 */
	private List<CouponDto> getUnReceiveCouponDtoList(String userUniqueCode) {
		QueryWrapper<Coupon> queryWrapper = new QueryWrapper<Coupon>().eq("issued_way", 3)
				.eq("status", 2);
		List<Coupon> couponList = this.baseMapper.selectList(queryWrapper);
		List<CouponDto> waitingReceiveCouponList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(couponList)) {
			for (Coupon coupon : couponList) {
				Integer couponId = coupon.getId();
				CouponDto couponDto = new CouponDto();
				//用户优惠券关系表中查询该优惠券是否存在
				QueryWrapper<UserCoupon> userCouponQueryWrapper = new QueryWrapper<UserCoupon>()
						.eq("userUniqueCode", userUniqueCode)
						.eq("coupon_id", couponId);
				List<UserCoupon> userCouponList = userCouponMapper.selectList(userCouponQueryWrapper);
				if (CollectionUtils.isEmpty(userCouponList)) {
					//不存在 存入未领取的 优惠券列表中
					BeanUtils.copyProperties(coupon, couponDto);
					waitingReceiveCouponList.add(couponDto);
				}

			}
		}
		return waitingReceiveCouponList;
	}

	@Override
	public Result<List<CouponDto>> getAlreadyReceivedCouponList(GetCouponListReq getCouponListReq) {
		String userUniqueCode = getCouponListReq.getUserUniqueCode();
		List<CouponDto> receivedCouponList = this.getReceivedCouponList(userUniqueCode);
		return Result.success(receivedCouponList);
	}

	/**
	 * 获取用户已经领取的优惠券列表
	 *
	 * @param userUniqueCode userUniqueCode
	 * @return List<CouponDto>
	 */
	private List<CouponDto> getReceivedCouponList(String userUniqueCode) {
		List<CouponDto> receivedCouponList = Lists.newArrayList();
		QueryWrapper<UserCoupon> userCouponQueryWrapper = new QueryWrapper<UserCoupon>()
				.eq("userUniqueCode", userUniqueCode);
		List<UserCoupon> userCouponList = userCouponMapper.selectList(userCouponQueryWrapper);

		if (CollectionUtils.isNotEmpty(userCouponList)) {
			for (UserCoupon userCoupon : userCouponList) {
				Integer couponId = userCoupon.getCouponId();
				//0-已使用 1-未使用 3-已过期
				Integer availableState = userCoupon.getAvailableState();
				//查询优惠券信息
				Coupon coupon = this.baseMapper.selectById(couponId);
				CouponDto couponDto = new CouponDto();
				if (coupon != null) {
					BeanUtils.copyProperties(coupon, couponDto);
				}
				couponDto.setUseStatus(availableState);
				receivedCouponList.add(couponDto);
			}
		}
		return receivedCouponList;
	}

	@Override
	public Result<CouponListDto> getCouponList(GetCouponListReq getCouponListReq) {
		//查询优惠券
		String userUniqueCode = getCouponListReq.getUserUniqueCode();
		CouponListDto couponListDto = new CouponListDto();
		//获取未领取的优惠券列表
		List<CouponDto> waitingReceiveCouponList = getUnReceiveCouponDtoList(userUniqueCode);
		if (CollectionUtils.isNotEmpty(waitingReceiveCouponList)) {
			couponListDto.setWaitingReceiveCouponList(waitingReceiveCouponList);
			int size = waitingReceiveCouponList.size();
			couponListDto.setUnReceiveCount(size);
		}

		//获取用户的优惠券列表
		List<CouponDto> receivedCouponList = getReceivedCouponList(userUniqueCode);
		if (CollectionUtils.isNotEmpty(receivedCouponList)) {
			couponListDto.setReceivedCouponList(receivedCouponList);
			int size = receivedCouponList.size();
			couponListDto.setReceiveCount(size);
		}

		return Result.success(couponListDto);
	}

	@Override
	public Result<CouponDetailDto> getCouponDetail(GetCouponDetailReq getCouponDetailReq) {
		Integer couponId = getCouponDetailReq.getCouponId();
		Coupon coupon = this.baseMapper.selectById(couponId);
		CouponDetailDto couponDetailDto = new CouponDetailDto();
		if (coupon != null) {
			//根据优惠券状态判断
			//状态待确定
			Integer status = coupon.getStatus();
			BeanUtils.copyProperties(coupon, couponDetailDto);
			CouponPreferentialStrategy couponPreferentialStrategy = getCouponPreferentialStrategy(couponId);
			CouponPreferentialStrategyDto couponPreferentialStrategyDto = copyPropertiesToNewModel(couponPreferentialStrategy, CouponPreferentialStrategyDto.class);
			if (couponPreferentialStrategyDto != null) {
				couponDetailDto.setCouponPreferentialStrategyDto(couponPreferentialStrategyDto);
			}
		}

		return Result.success(couponDetailDto);
	}

	@Override
	public Result<CoursePreferentialInfoDto> getCoursePreferentialInfo(GetCoursePreferentialInfoReq getCoursePreferentialInfoReq) {
		Integer courseId = getCoursePreferentialInfoReq.getCourseId();
		String userUniqueCode = getCoursePreferentialInfoReq.getUserUniqueCode();
//		//调用用户服务 获取 用户的所有有效优惠券
//		//循环获取 当前课程可使用优惠券
//		//全场优惠卷 计算优惠价格  打折
//		//全场优惠券 计算优惠价格 满赠 判断课程价格 是否达到满减的价格
//		//限定课程优惠券 判断是否支持当前课程   支持 打折 计算优惠价格
		//限定课程优惠券 判断是否支持当前课程   支持 满减 课程价格是否达到满减价
		//课程金额
		Integer coursePrice = getCoursePreferentialInfoReq.getCoursePrize();
		List<Integer> preferentialPrice = Lists.newArrayList();
		List<CouponDto> receivedCouponList = this.getReceivedCouponList(userUniqueCode);
		if (CollectionUtils.isEmpty(receivedCouponList)) {
			//无可用优惠券
			return Result.success(new CoursePreferentialInfoDto()
					.setPreferentialDesc(""));
		}
		//可使用的优惠券列表
		List<CouponDto> canUseCouponList = receivedCouponList.stream().filter(couponDto -> couponDto.getUseStatus() == 1).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(canUseCouponList)) {
			//无可用优惠券
			return Result.success(new CoursePreferentialInfoDto()
					.setPreferentialDesc(""));
		}

		for (CouponDto couponDto : canUseCouponList) {
			//优惠券ID
			Integer couponId = couponDto.getId();
			Coupon coupon = this.baseMapper.selectById(couponId);
			if (coupon != null) {
				//1.打折 2. 满减 3:立减
				Integer preferentialWay = coupon.getPreferentialWay();

				QueryWrapper<CouponPreferentialStrategy> queryWrapper = new
						QueryWrapper<CouponPreferentialStrategy>()
						.eq("coupon_id", couponId)
						.eq("status", 1);
				CouponPreferentialStrategy couponPreferentialStrategy = couponPreferentialStrategyMapper.selectOne(queryWrapper);
				if (couponPreferentialStrategy == null) {
					//无优惠策略
					continue;
				}
				//优惠范围 1：全场 2：指定商品
				Integer range = coupon.getRange();
				if (range == 1) {
					//直接计算优惠价
					//1.打折 2. 满减 3:立减
					Integer price = this.calculatePrice(couponPreferentialStrategy, preferentialWay, coursePrice);
					preferentialPrice.add(price);
				} else if (range == 2) {
					//指定商品
					if (!checkCouponSupportCourse(courseId, couponId)) {
						continue;
					}
					//1.打折 2. 满减 3:立减
					Integer price = this.calculatePrice(couponPreferentialStrategy, preferentialWay, coursePrice);
					preferentialPrice.add(price);
				}
			}
		}

		//最大优惠价`
		Integer max;
		if (CollectionUtils.isNotEmpty(preferentialPrice)) {
			max = Collections.max(preferentialPrice);
			if (0==max) {
				CoursePreferentialInfoDto coursePreferentialInfoDto = new CoursePreferentialInfoDto();
				coursePreferentialInfoDto.setPreferentialDesc("");
				return Result.success(coursePreferentialInfoDto);
			}
		} else {
			CoursePreferentialInfoDto coursePreferentialInfoDto = new CoursePreferentialInfoDto();
			coursePreferentialInfoDto.setPreferentialDesc("");
			return Result.success(coursePreferentialInfoDto);
		}
		//返回优惠信息描述
		String desc = String.format(CouponConstant.preferentialDesc, max);
		CoursePreferentialInfoDto coursePreferentialInfoDto = new CoursePreferentialInfoDto();
		coursePreferentialInfoDto.setPreferentialDesc(desc);
		return Result.success(coursePreferentialInfoDto);
	}

	/**
	 * 计算优惠价格
	 *
	 * @param couponPreferentialStrategy couponPreferentialStrategy 优惠策略
	 * @param preferentialWay            preferentialWay
	 * @param coursePrice                coursePrice
	 * @return BigDecimal
	 */
	private Integer calculatePrice(CouponPreferentialStrategy couponPreferentialStrategy,
									  Integer preferentialWay,
									  Integer coursePrice) {
		//1.打折 2. 满减 3:立减
		if (couponPreferentialStrategy == null) {
			return 0;
		}
		//满金额
		Integer fullPrice = couponPreferentialStrategy.getFullPrice();
		//减金额
		Integer reducePrice = couponPreferentialStrategy.getReducePrice();
		//打折
		BigDecimal discount = couponPreferentialStrategy.getDiscount();
		switch (preferentialWay) {
			case 1:
				//优惠后价格
				Integer priceAfterDiscount  = Double.valueOf(discount.doubleValue()*coursePrice).intValue();
				//优惠的价格
				int preferentialPrize = coursePrice - priceAfterDiscount;
				if (preferentialPrize <= 0) {
					return 0;
				}
				return preferentialPrize;
			case 2:
				//满减
				if (coursePrice.compareTo(fullPrice) >= 0) {
					return reducePrice;
				} else {
					return 0;
				}

			case 3:
				return reducePrice;
			default:
		}
		return 0;
	}


	@Override
	public Result<CanUseCouponDetailDto> getCanUseCouponDetail(GetCanUseCouponListReq getCanUseCouponListReq) {
		CanUseCouponDetailDto canUseCouponDetailDto = new CanUseCouponDetailDto();
		String userUniqueCode = getCanUseCouponListReq.getUserUniqueCode();
		//查询用户优惠券列表
		QueryWrapper<UserCoupon> userCouponQueryWrapper = new QueryWrapper<UserCoupon>()
				.eq("userUniqueCode", userUniqueCode)
				.eq("available_state", 1);
		List<UserCoupon> userCouponList = userCouponMapper.selectList(userCouponQueryWrapper);
		List<CouponInfoDto> couponInfoDtoList = new LinkedList<>();
		if (CollectionUtils.isNotEmpty(userCouponList)) {

			for (UserCoupon userCoupon : userCouponList) {
				CouponInfoDto couponInfoDto = new CouponInfoDto();
				Integer couponId = userCoupon.getId();
				Coupon coupon = this.baseMapper.selectById(couponId);
				if (coupon == null) {
					//优惠券信息为空 跳出当前循环
					continue;
				}



				BeanUtils.copyProperties(coupon, couponInfoDto);
				//查询优惠券优惠策略
				CouponPreferentialStrategy couponPreferentialStrategy = setCouponPreferentialStrategyDtoAndGetCouponPreferentialStrategy(couponInfoDto, couponId);
				//检查 优惠券是否支持该课程 并且设置优惠价格
				Boolean canAdd = checkAndSetCalculatePrice(couponInfoDto, couponPreferentialStrategy, coupon, getCanUseCouponListReq);
				if (canAdd) {
					couponInfoDtoList.add(couponInfoDto);
				}
			}
		}
		if (CollectionUtils.isNotEmpty(couponInfoDtoList)) {
			//按照优惠列表
			canUseCouponDetailDto.setCouponInfoDtoList(couponInfoDtoList);
			int size = couponInfoDtoList.size();
			canUseCouponDetailDto.setAvailableCount(size);
		}

		return Result.success(canUseCouponDetailDto);
	}

	/**
	 * 设置setCouponPreferentialStrategyDto 并获取优惠策略
	 *
	 * @param couponInfoDto couponInfoDto
	 * @param couponId      couponId
	 * @return CouponPreferentialStrategy
	 */
	private CouponPreferentialStrategy setCouponPreferentialStrategyDtoAndGetCouponPreferentialStrategy(CouponInfoDto couponInfoDto, Integer couponId) {

		CouponPreferentialStrategy couponPreferentialStrategy = getCouponPreferentialStrategy(couponId);
		CouponPreferentialStrategyDto couponPreferentialStrategyDto = copyPropertiesToNewModel(couponPreferentialStrategy, CouponPreferentialStrategyDto.class);
		if (couponPreferentialStrategyDto != null) {
			couponInfoDto.setCouponPreferentialStrategyDto(couponPreferentialStrategyDto);
		}
		return couponPreferentialStrategy;
	}

	/**
	 * 获取优惠券优惠策略
	 *
	 * @param couponId couponId
	 * @return CouponPreferentialStrategy
	 */
	public CouponPreferentialStrategy getCouponPreferentialStrategy(Integer couponId) {
		QueryWrapper<CouponPreferentialStrategy> queryWrapper = new
				QueryWrapper<CouponPreferentialStrategy>()
				.eq("coupon_id", couponId)
				.eq("status", 1);
		return couponPreferentialStrategyMapper.selectOne(queryWrapper);
	}

	/**
	 * 检查并设置课程优惠价格
	 *
	 * @param couponInfoDto              couponInfoDto
	 * @param couponPreferentialStrategy couponPreferentialStrategy
	 * @param coupon                     coupon
	 * @param getCanUseCouponListReq     getCanUseCouponListReq
	 */
	private Boolean checkAndSetCalculatePrice(CouponInfoDto couponInfoDto, CouponPreferentialStrategy couponPreferentialStrategy, Coupon coupon, GetCanUseCouponListReq getCanUseCouponListReq) {

		//排除 不支持的优惠券
		//1 不支持该课程的
		//2 满减 没有达到要求的
		//1：全场 2：指定商品  3：指定科目
		Integer couponId = coupon.getId();
		Integer preferentialWay = coupon.getPreferentialWay();
		Integer courseId = getCanUseCouponListReq.getCourseId();
		Integer orderAmount = getCanUseCouponListReq.getOrderAmount();
		Integer range = coupon.getRange();
		if (range == 1) {
			//全场
			//优惠方式 1.打折 2. 满减3:立减
			return customCalculatePrice(couponInfoDto, couponPreferentialStrategy, preferentialWay, orderAmount);
		} else {
			if (!checkCouponSupportCourse(courseId, couponId)) {
				//优惠券不支持当前课程 执行下一次循环
				return false;
			} else {
				//优惠方式 1.打折 2. 满减3:立减
				return customCalculatePrice(couponInfoDto, couponPreferentialStrategy, preferentialWay, orderAmount);
			}
		}

	}

	private boolean customCalculatePrice(CouponInfoDto couponInfoDto, CouponPreferentialStrategy couponPreferentialStrategy, Integer preferentialWay, Integer orderAmount) {
		if (preferentialWay == 2) {
			//满减类型的优惠活动 是否达到优惠券使用条件
			Integer fullPrice = couponPreferentialStrategy.getFullPrice();
			//-1, 0, or 1
			// less than, equal to, or greater than
			if (orderAmount.compareTo(fullPrice) < 0) {
				//订单金额 小于 满减金额 无法使用 该优惠券
				return false;
			} else {
				//可以使用优惠券 进行 添加 同时计算 优惠券金额
				Integer calculatePrice = calculatePrice(couponPreferentialStrategy, preferentialWay, orderAmount);
				couponInfoDto.setPreferentialPrize(calculatePrice);
			}
		} else {
			Integer calculatePrice = calculatePrice(couponPreferentialStrategy, preferentialWay, orderAmount);
			couponInfoDto.setPreferentialPrize(calculatePrice);
		}
		return false;
	}


	/**
	 * 检查优惠券是否支持该课程
	 *
	 * @param courseId courseId
	 * @param couponId couponId
	 * @return true 支持 false 不支持
	 */
	private boolean checkCouponSupportCourse(Integer courseId, Integer couponId) {
		QueryWrapper<CouponGoodsRelation> goodsRelationQueryWrapper = new QueryWrapper<CouponGoodsRelation>()
				.eq("coupon_id", couponId)
				.eq("delete_flag", 0);
		CouponGoodsRelation couponGoodsRelation = couponGoodsRelationMapper.selectOne(goodsRelationQueryWrapper);

		if (couponGoodsRelation == null) {
			return true;
		}

		Integer goodsRelationCourseId = couponGoodsRelation.getGoodsId();
		//是否支持该课程
		return courseId.equals(goodsRelationCourseId);
	}


	@Override
	public Result<MyCouponListDto> getMyCouponList(GetMyCouponListReq getMyCouponListReq) {
		MyCouponListDto myCouponListDto = new MyCouponListDto();
		String userUniqueCode = getMyCouponListReq.getUserUniqueCode();
		List<CouponDto> receivedCouponList = getReceivedCouponList(userUniqueCode);
		if (CollectionUtils.isNotEmpty(receivedCouponList)) {
			//未使用列表
			List<CouponDto> unusedCouponList = receivedCouponList.stream().filter(couponDto -> couponDto.getUseStatus() == 1).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(unusedCouponList)) {
				myCouponListDto.setUnUsedCouponList(unusedCouponList);
				int size = unusedCouponList.size();
				myCouponListDto.setUnUsedCount(size);
			}
			//已经使用列表
			List<CouponDto> usedCouponList = receivedCouponList.stream().filter(couponDto -> couponDto.getUseStatus() == 0).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(usedCouponList)) {
				myCouponListDto.setUsedCouponList(usedCouponList);
				int size = usedCouponList.size();
				myCouponListDto.setUsedCount(size);
			}
		}
		return Result.success(myCouponListDto);
	}

	@Override
	public Result<Object> receive(ReceiveReq receiveReq) {
		Integer couponId = receiveReq.getCouponId();
		String userUniqueCode = receiveReq.getUserUniqueCode();
		Coupon coupon = this.baseMapper.selectById(couponId);
		if(coupon!=null){
			LocalDateTime startTime = coupon.getStartTime();
			LocalDateTime endTime = coupon.getEndTime();
			UserCoupon userCoupon = new UserCoupon()
					.setUserUniqueCode(userUniqueCode)
					.setCouponId(couponId)
					.setGainDate(LocalDateTime.now())
					.setStartTime(startTime)
					.setEndTime(endTime)
					.setAvailableState(1);
			int insert = userCouponMapper.insert(userCoupon);
			if(insert<=0){
				return Result.fail(CodeMessage.RECEIVE_EXCEPTION);
			}
		}

		return Result.success("领取成功");
	}
}
