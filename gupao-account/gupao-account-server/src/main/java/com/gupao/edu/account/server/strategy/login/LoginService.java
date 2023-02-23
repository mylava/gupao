package com.gupao.edu.account.server.strategy.login;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.gupao.edu.account.client.req.LoginReq;
import com.gupao.edu.account.client.resp.LoginResp;
import com.gupao.edu.account.server.jwt.JwtTokenUtils;
import com.gupao.edu.common.result.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Class: 登录接口
 *
 * @Author: wangzhong
 * @Date: 2020-03-25 20:34
 */
public interface LoginService {
	/**
	 * 抽取公共生成token方法
	 *
	 * @param userUniqueCode 用户唯一id
	 * @param secret         加密密钥
	 * @return
	 */
	static String getAccessToken(String userUniqueCode, String secret) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("userUniqueCode", userUniqueCode);
		return JwtTokenUtils.creatJwtToken(secret, JSON.toJSONString(map));
	}

	/**
	 * 登录service容器
	 */
	Map<Integer, LoginService> container = new HashMap<>();

	Result<LoginResp> login(LoginReq loginReq);

	/**
	 * 登录类型枚举
	 */
	enum LoginType {

		ACCOUNT_LOGIN(0, "moblie"), PHONE_LOGIN(1, "phone"), WECHAT_LOGIN(2, "weChat"), QQ_LOGIN(3, "qq");

		private Integer code;
		private String msg;

		LoginType(Integer code, String msg) {
			this.code = code;
			this.msg = msg;
		}


		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}

