package com.gupao.edu.account.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.gupao.edu.account.client.resp.AreaResp;
import com.gupao.edu.account.client.resp.DictResp;
import com.gupao.edu.account.server.service.CommonService;
import com.gupao.edu.lotus.client.facade.lotus.SMSFegin;
import com.gupao.edu.common.area.entity.Area;
import com.gupao.edu.common.area.mapper.AreaMapper;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.AreaPrefix;
import com.gupao.edu.common.cache.key.DictPrefix;
import com.gupao.edu.common.cache.key.UserPrefix;
import com.gupao.edu.common.dict.entity.Dict;
import com.gupao.edu.common.dict.mapper.DictMapper;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.lotus.client.message.SmsMessage;
import com.gupao.edu.lotus.client.message.VerifyCodeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/26 16:34
 */

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
	private static String ENCODING = "UTF-8";
	@Value("${sms_yunpian_tz_apikey}")
	private String appkey;
	@Value("${message_templateId}")
	private String templateId;
	@Autowired
	private SMSFegin smsFegin;

	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private DictMapper dictMapper;

	//TODO 1.添加[业务类型枚举]类 2.验证手机号码是否有效 3.为了防止跳过验证，短信在缓存中保存5分钟，执行发送短信逻辑
	@Override
	public Result sendMessage(String mobile, Integer bizCode) {
		List<String> list = new ArrayList<>();
		list.add(mobile);
		SmsMessage smsMessage = new SmsMessage();
		smsMessage.setMobiles(list);
		smsMessage.setTemplateId(templateId);
		smsMessage.setBizCode(bizCode);
		return smsFegin.sendMessage(smsMessage);
	}

	/**
	 * 验证短信
	 *
	 * @param mobile
	 * @param bizCode
	 */
	@Override
	public Result verifyCode(String mobile, Integer bizCode, String msg) throws Exception {
		return smsFegin.verifyCode(new VerifyCodeMessage(mobile,bizCode,msg));
	}

	@Override
	public int getArticleNum(String userUniqueCode) {
		String article = CacheUtil.get(UserPrefix.USER_ARTICLE, userUniqueCode);
		return StringUtils.isEmpty(article) ? 0 : Integer.valueOf(article);
	}

	@Override
	public int getAskNum(String userUniqueCode) {
		String question = CacheUtil.get(UserPrefix.USER_QUESTION, userUniqueCode);
		return StringUtils.isEmpty(question) ? 0 : Integer.valueOf(question);
	}

	@Override
	public int getCourseNum(String userUniqueCode) {
		String course = CacheUtil.get(UserPrefix.USER_COURSE, userUniqueCode);
		return StringUtils.isEmpty(course) ? 0 : Integer.valueOf(course);
	}

	@Override
	public int getMessageNum(String userUniqueCode) {
		String message = CacheUtil.get(UserPrefix.USER_MESSAGER, userUniqueCode);
		return StringUtils.isEmpty(message) ? 0 : Integer.valueOf(message);
	}

	@Override
	public int getOrderNum(String userUniqueCode) {
		String order = CacheUtil.get(UserPrefix.USER_ORDER, userUniqueCode);
		return StringUtils.isEmpty(order) ? 0 : Integer.valueOf(order);
	}

	@Override
	public int getFavoriteNum(String userUniqueCode) {
		return getCourseNum(userUniqueCode) + getArticleNum(userUniqueCode) + getAskNum(userUniqueCode);
	}

	@Override
	public int getCouponNum(String userUniqueCode) {
		String coupon = CacheUtil.get(UserPrefix.USER_COUPON, userUniqueCode);
		return StringUtils.isEmpty(coupon) ? 0 : Integer.valueOf(coupon);
	}

	@Override
	public List<AreaResp> getAreaList(Integer id) {
		if (id != null && id != 0) {
			//id 有效 查询ID对应的区域列表
			Boolean exists = CacheUtil.exists(AreaPrefix.areaPrefix, String.valueOf(id));
			if (exists) {
				String ss = CacheUtil.get(AreaPrefix.areaPrefix, String.valueOf(id));
				if (StringUtils.isEmpty(ss)) {
					//缓存为空 或者异常 再次查询 设置到缓存
					List<AreaResp> areaRespList = queryAreaList(id);
					CacheUtil.set(AreaPrefix.areaPrefix, String.valueOf(id), JSON.toJSONString(areaRespList));
					return areaRespList;
				}
				return JSON.parseObject(ss, new TypeReference<List<AreaResp>>() {
				});
			} else {
				List<AreaResp> areaRespList = queryAreaList(id);
				CacheUtil.set(AreaPrefix.areaPrefix, String.valueOf(id), JSON.toJSONString(areaRespList));
				return areaRespList;
			}

		}
		List<AreaResp> areaRespList = queryAreaList(0);
		CacheUtil.set(AreaPrefix.areaPrefix, String.valueOf(id), JSON.toJSONString(areaRespList));
		return areaRespList;
	}

	/**
	 * 查询区域列表
	 *
	 * @param parentId parentId
	 * @return List<AreaResp>
	 */
	public List<AreaResp> queryAreaList(Integer parentId) {

		List<AreaResp> areaRespList = Lists.newArrayList();
		QueryWrapper<Area> queryWrapper = new QueryWrapper<Area>()
				.eq("parent_id", parentId);
		List<Area> areaList = areaMapper.selectList(queryWrapper);
		if (CollectionUtils.isNotEmpty(areaList)) {
			areaList.parallelStream().forEach(area -> {
				Integer areaId = area.getId();
				String name = area.getName();
				AreaResp areaResp = new AreaResp()
						.setId(areaId)
						.setName(name);
				QueryWrapper<Area> queryCityListWrapper = new QueryWrapper<Area>()
						.eq("parent_id", areaId);
				List<Area> cityList = areaMapper.selectList(queryCityListWrapper);
				if (CollectionUtils.isNotEmpty(cityList)) {
					List<AreaResp> collect = cityList.parallelStream().map(city -> new AreaResp(city.getId(), city.getName(), null)).collect(Collectors.toList());
					areaResp.setCityList(collect);
				}
				areaRespList.add(areaResp);
			});
		}
		return areaRespList;

	}

	@Override
	public List<DictResp> getDictList(String categoryCode) {
		if (StringUtils.isNotEmpty(categoryCode)) {
			Boolean exists = CacheUtil.exists(DictPrefix.dictPrefix, categoryCode);
			if (exists) {
				String s = CacheUtil.get(DictPrefix.dictPrefix, categoryCode);
				if (StringUtils.isEmpty(s)) {
					List<DictResp> dictRespList = queryDictList(categoryCode);
					if (CollectionUtils.isNotEmpty(dictRespList)) {
						CacheUtil.set(DictPrefix.dictPrefix, categoryCode, JSON.toJSONString(dictRespList));
					}
				}
				return JSON.parseObject(s, new TypeReference<List<DictResp>>() {
				});


			}
			List<DictResp> dictRespList = queryDictList(categoryCode);
			if (CollectionUtils.isNotEmpty(dictRespList)) {
				CacheUtil.set(DictPrefix.dictPrefix, categoryCode, JSON.toJSONString(dictRespList));
			}

			return dictRespList;
		}
		return null;
	}

	public List<DictResp> queryDictList(String categoryCode) {
		QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>()
				.eq("category_code", categoryCode);
		List<Dict> dictList = dictMapper.selectList(queryWrapper);
		if (CollectionUtils.isNotEmpty(dictList)) {
			return dictList.parallelStream().map(dict -> new DictResp(dict.getDictName(), dict.getDictValue())).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
}
