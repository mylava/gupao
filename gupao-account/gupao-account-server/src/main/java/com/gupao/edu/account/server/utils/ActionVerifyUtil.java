package com.gupao.edu.account.server.utils;

import com.alibaba.fastjson.JSON;
import com.dingxianginc.ctu.client.CaptchaClient;
import com.dingxianginc.ctu.client.model.CaptchaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Class: 滑块行为验证
 *
 * @Author: wangzhong
 * @Date: 2020-03-29 16:00
 */
@Component
@Slf4j
public class ActionVerifyUtil {
	@Value("${dingxiang_app_id:0}")
	private String appId;

	@Value("${dingxiang_app_key}")
	private String appKey;

	/**
	 * 滑块验证
	 *
	 * @param token    前端token
	 * @param clientIp 客户端ip
	 * @return
	 */
	public boolean verifyAction(String token, String clientIp) {
		CaptchaClient captchaClient = new CaptchaClient(appId, appKey);

		CaptchaResponse response = null;
		try {
			response = captchaClient.verifyToken(token, clientIp);
		} catch (Exception e) {
			log.error("滑块验证token失败,{}", e);
			return false;
		}
		log.info("滑块验证结果：{}", JSON.toJSONString(response));
		if (null != response && null != response.getResult()) {
			return response.getResult();
		} else {
			return false;
		}
	}


}