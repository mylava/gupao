package com.gupao.edu.lotus.server.service.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.SmsPrefix;
import com.gupao.edu.common.constant.SmsChannel;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;


import com.gupao.edu.lotus.client.message.SmsMessage;
import com.gupao.edu.lotus.client.message.VerifyCodeMessage;
import com.gupao.edu.lotus.client.message.YunpianSmsTemplate;
import com.gupao.edu.lotus.server.utils.JavaSmsApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;


/**
 * @Description: 短信服务Service
 * @Author： wz
 * @Date: 2019-04-01 11:09
 **/
@Component
public class SmsService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;
	@Value("${sms_yunpian_tz_apikey:bdd46e7c95fad7dfb71b58a5f0103f3f}")
	private String appkey;
	@Value("${needSend:true}")
	private Boolean needSend;


	/**
	 * 发送短信消息
	 *
	 * @param smsMessage 消息实体
	 * @return
	 */
	public Result sendSmsMsg(SmsMessage smsMessage) {
		if (!needSend) {
			throw new RuntimeException("短信发送失败, 测试环境");
		}
		// 生成短信并且缓存
		String verifyCode = String.valueOf(new Random().nextInt(8999) + 1000);//生成短信验证码

		// 发送短信
		String text = "#code#=" + verifyCode;
		int code = JavaSmsApi.singleSend(appkey,smsMessage.getMobiles().get(0),text,smsMessage.getTemplateId());
		if (code == 0) {
			// 放入缓存
			CacheUtil.set(SmsPrefix.smsVerifyCodePrefix,smsMessage.getMobiles().get(0)+"-"+smsMessage.getBizCode(),verifyCode);
			return Result.success();
		}
		return Result.fail(CodeMessage.SMS_SEND_FAIL);
	}

	/**
	 * 获取云片模板信息
	 *
	 * @return
	 */
	public List<YunpianSmsTemplate> findYunpianTemple() {
		// String responseStr = restTemplate.postForObject(smsUrl + "/sms/message/getAllTpl", SmsChannel.SMS_YUNPIAN.getCode(), String.class);
		// JSONObject templeJSON = JSON.parseObject(responseStr);

		// String templeStr = templeJSON.getString("data");

		// logger.info("templeStr:" + templeStr);

		// return JSON.parseArray(templeStr, YunpianSmsTemplate.class);
		return null;
	}

	public Result verifyCode(VerifyCodeMessage message) throws Exception {
		String verifyCode = CacheUtil.get(SmsPrefix.smsVerifyCodePrefix, message.getMobile() + "-" + message.getBizCode());
		if (verifyCode == null) {
			return Result.fail(CodeMessage.TIMEOUT_VERIFY_CODE_CHECK);
		}
		if (!message.getMsg().equals(verifyCode)) {
			return Result.fail(CodeMessage.VERIFY_CODE_FAIL_CHECK);
		}
		return Result.success();
	}
}
