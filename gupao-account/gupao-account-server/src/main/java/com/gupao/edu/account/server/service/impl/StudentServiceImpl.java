package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.entity.UserInfoDetail;
import com.gupao.edu.account.client.req.EntranceReq;
import com.gupao.edu.account.client.resp.*;
import com.gupao.edu.account.server.mapper.UserInfoDetailMapper;
import com.gupao.edu.account.server.mapper.UserMapper;
import com.gupao.edu.account.server.service.StudentService;
import com.gupao.edu.common.constant.DictNameConstants;
import com.gupao.edu.common.dict.mapper.DictMapper;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.order.client.entity.OrderSupplement;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 学籍业务处理类
 *
 * @author leon
 * @date 2020-04-27 13:51:26
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

	private final DictMapper dictMapper;
	private final UserMapper userMapper;
	private final UserInfoDetailMapper userInfoDetailMapper;

	@Autowired
	public StudentServiceImpl(DictMapper dictMapper, UserMapper userMapper, UserInfoDetailMapper userInfoDetailMapper) {
		this.dictMapper = dictMapper;
		this.userMapper = userMapper;
		this.userInfoDetailMapper = userInfoDetailMapper;
	}

	@Override
	public SchoolRollResp schoolRoll(String userUniqueCode) {
		//1.关联订单补充表查询审核状态不等于-1的 2.关联字典表 查询学员协议、入学手册
		SchoolRollResp schoolRollResp = new SchoolRollResp();
		QueryWrapper<OrderSupplement> queryWrapper = new QueryWrapper<OrderSupplement>()
				.eq("user_unique_code", userUniqueCode)
				.in("audit_state", 0, 1);
		//feign 调用
		List<OrderSupplement> orderSupplementList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(orderSupplementList)) {
			List<SchoolRollVO> schoolRollVoList = Lists.newArrayList();
			//查询用户信息
			User user = userMapper.selectByUniqueCode(userUniqueCode);
			if (user != null) {
				String avatar = user.getAvatar();
				//头像 头像为空 或者设置默认头像
				if (StringUtils.isNotEmpty(avatar)) {
					schoolRollResp.setAvatar(avatar);
				}
				//学号
				String studentNo = user.getStudentNo();
				if (StringUtils.isNotEmpty(studentNo)) {
					schoolRollResp.setStudentNo(studentNo);
				}

			}
			//协议地址
			String agreementUrl = dictMapper.getValueByName(DictNameConstants.AGREEMENT_URL);
			for (OrderSupplement orderSupplement : orderSupplementList) {
				//课程ID
				Integer courseId = orderSupplement.getCourseId();
				Integer auditState = orderSupplement.getAuditState();
				LocalDateTime createTime = orderSupplement.getCreateTime();

				//查询课程对应的入学手册
				//前缀+课程ID 查找课程对应的入学手册
				String noteBook = dictMapper.getValueByName(DictNameConstants.NOTEBOOK + courseId);
				SchoolRollVO schoolRollVO = new SchoolRollVO()
						.setAuditState(auditState)
						//TODO  feign 调用课程查询课程名称
						.setCourseName("")
						.setJoinTime(createTime.toLocalDate())
						//入学手册
						.setNotebook(noteBook)
						//学院协议
						.setProtocol(agreementUrl);
				schoolRollVoList.add(schoolRollVO);
			}
			schoolRollResp.setSchoolRollVOList(schoolRollVoList);
			return schoolRollResp;
		}

		return null;
	}

	@Override
	public CardResp card(String userUniqueCode) {
		// 1.查询用户头像、昵称、卡号（用户表中学号） 2.关联字典表 查询黑卡背景图url、黑卡富文本url
		CardResp cardResp = new CardResp();
		//查询用户信息
		User user = userMapper.selectByUniqueCode(userUniqueCode);
		if (user != null) {
			//头像
			String avatar = user.getAvatar();
			//昵称
			String nickName = user.getNickName();
			//学号
			String studentNo = user.getStudentNo();
			cardResp.setAvatar(avatar);
			cardResp.setNickName(nickName);
			cardResp.setCardNo(studentNo);
			//黑卡背景图
			String blackCardBackGroundImageUrl = dictMapper.getValueByName(DictNameConstants.BLACK_CARD_BACKGROUND_IMAGE_URL);
			if (StringUtils.isNotEmpty(blackCardBackGroundImageUrl)) {
				cardResp.setCardImgUrl(blackCardBackGroundImageUrl);
			}
			//黑卡描述
			String blackCardDescriptionUrl = dictMapper.getValueByName(DictNameConstants.BLACK_CARD_DESCRIPTION_URL);
			if (StringUtils.isNotEmpty(blackCardDescriptionUrl)) {
				cardResp.setCardTextUrl(blackCardDescriptionUrl);
			}
		}

		return cardResp;
	}

	@Override
	public RecommendSalesmanResp recommendSalesman(String recommendCode) {
		// 1.暂时从字典表获取  后期将销售的数据维护在运营系统中，通过feign获取
		RecommendSalesmanResp recommendSalesmanResp = new RecommendSalesmanResp();
		String recommendTeacherId = dictMapper.getValueByName(recommendCode);
		if (StringUtils.isEmpty(recommendTeacherId)) {
			return null;
		}
		recommendSalesmanResp.setSalesmanId(Integer.valueOf(recommendTeacherId));
		//TODO 从lecturer表查询昵称
		recommendSalesmanResp.setSalesman("");
		return recommendSalesmanResp;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result<String> entrance(EntranceReq entranceReq) {
		String userUniqueCode = entranceReq.getUserUniqueCode();
		// 1.保存用户信息 2.发送消息到订单模块进行补充订单处理
		String name = entranceReq.getName();
		//生日
		LocalDate birthday = entranceReq.getBirthday();
		//性别
		Integer gender = entranceReq.getGender();
		//学历
		Integer education = entranceReq.getEducation();
		//省
		Integer provinceId = entranceReq.getProvinceId();
		//城市
		Integer cityId = entranceReq.getCityId();
		String qq = entranceReq.getQq();
		//所在公司
		String employer = entranceReq.getEmployer();
		Integer jobTitle = entranceReq.getJobTitle();
		//工作年限
		Integer seniority = entranceReq.getSeniority();
		//当前薪水
		Integer currentSalary = entranceReq.getCurrentSalary();
		//期望薪水
		Integer expectSalary = entranceReq.getExpectSalary();
		//课程ID
		Integer courseId = entranceReq.getCourseId();
		//试听讲师ID
		Integer salesmanId = entranceReq.getSalesmanId();
		Integer recommendUserId = entranceReq.getRecommendUserId();

		Integer lectureId = entranceReq.getLectureId();

		String solveProblem = entranceReq.getSolveProblem();
		//了解渠道
		Integer realizeChannel = entranceReq.getRealizeChannel();
		//试听次数
		Integer auditionCounts = entranceReq.getAuditionCounts();
		//TODO 学籍插入判断 对应的学籍是否已经存在
		UserInfoDetail userInfoDetail = new UserInfoDetail()
				.setUserName(name)
				.setUserUniqueCode(userUniqueCode)
				.setBirthday(birthday)
				.setGender(gender)
				.setWorkCity("")
				.setProvinceId(provinceId)
				.setCityId(cityId)
				.setEducation(education)
				.setQq(qq)
				.setEmployer(employer)
				.setSeniority(seniority)
				.setJobTitle(jobTitle)
				.setCurrentSalary(currentSalary)
				.setExpectSalary(expectSalary);
		int insert = userInfoDetailMapper.insert(userInfoDetail);
		log.info("用户:{},入学记录新增:{}","",(insert>0?"成功":"失败"));
		//TODO 发送消息到订单模块进行补充订单处理
		return Result.success("入学成功");
	}

	@Override
	public AgreementResp getAgreement(String userUniqueCode) {
		//字典表获取协议
		String value = dictMapper.getValueByName(DictNameConstants.AGREEMENT_URL);
		return new AgreementResp()
				.setUrl(value);
	}

	@Override
	public String test() {
		return dictMapper.getValueByName(DictNameConstants.AGREEMENT_URL);
	}
}
