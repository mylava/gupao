package com.gupao.edu.account.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ThirdPartyLoginHelper {


	@Value("${accessTokenURL_qq}")
	private String accessTokenURLqq;

	@Value("${app_id_qq}")
	private String appIdQq;

	@Value("${app_key_qq}")
	private String appKeyQq;

	@Value("${redirect_url_qq}")
	private String redirectUrlQq;

	@Value("${authorizeURL_qq}")
	private String authorizeUrlQq;

	@Value("${getOpenIDURL_qq}")
	private String getOpenIDUrlQq;

	@Value("${getUserInfoURL_qq}")
	private String getUserInfoUrlQq;

	@Value("${accessTokenURL_wechat}")
	public String accessTokenUrlWechat;

	@Value("${app_id_wechat}")
	public String appIdWechat;

	@Value("${app_key_wechat}")
	public String appkeyWechat;

	@Value("${redirect_url_wechat}")
	public String redirecturlWechat;

	@Value("${getUserInfoURL_wechat}")
	public String getUserInfoURLWechat;

	@Value("${authorizationURL_wechat}")
	public String authorizationUrlWeChat;
	@Autowired
	private RestTemplate restTemplate;

	private static final String WHCHATSTATE = "wechat_bind";
	private static final String QQSTATE = "qq_bind";


	/**
	 * 获得qq/微信授权地址
	 *
	 * @param request
	 * @return
	 */
	public String getAuthorizationUrl(HttpServletRequest request) {
		String redirectUri = request.getParameter("redirectUri");
		String type = request.getParameter("type");
		String state = ("1".equals(type)) ? WHCHATSTATE : QQSTATE;
		String weChatParam = "?scope=snsapi_userinfo&response_type=code&appid={0}&redirect_uri={1}&state={2}#wechat_redirect";
		String QQParam = "?response_type=code&client_id={0}&redirect_uri={1}&state={2}";
		String paramUrl = MessageFormat.format("1".equals(type) ? weChatParam : QQParam, "1".equals(type) ? appIdWechat : appIdQq, URLEncoder.encode(redirectUri), state);
		String returnUrl=("1".equals(type) ? authorizationUrlWeChat : authorizeUrlQq) + paramUrl;
		log.info("授权地址:{}",returnUrl);
		return returnUrl;
	}

	/**
	 * 获取QQ的认证token和用户OpenID
	 *
	 * @param code 账号
	 * @return
	 */
	public final Result<String> getQQOpenid(String code, String state) {
		if (!QQSTATE.equals(state)) {
			return Result.fail(CodeMessage.THIRD_PARTY_AUTH_ERROR);
		}
		Map<String, String> map = new HashMap<>();
		// 获取令牌
		String paramUrl = "?grant_type=authorization_code&client_id={0}&client_secret={1}&code={2}&redirect_uri={3}";
		paramUrl = MessageFormat.format(paramUrl, appIdQq, appKeyQq, code, redirectUrlQq);
		String tokenUrl = accessTokenURLqq + paramUrl;
		log.info(" getQQTokenUrl:" + tokenUrl);
		String tokenRst = HttpUtil.sendGet(tokenUrl);
		log.info(" tokenRes:" + tokenRst);
		if (StringUtils.contains(tokenRst, "access_token")) {
			Map<String, String> tokenMap = toMap(tokenRst);
			map.put("access_token", tokenMap.get("access_token"));
			map.put("expires_in", tokenMap.get("expires_in"));
			map.put("refresh_token", tokenMap.get("refresh_token"));
			// 获取QQ用户的唯一标识openID
			String openIdUrl = getOpenIDUrlQq;
			openIdUrl = openIdUrl + "?access_token=" + tokenMap.get("access_token");
			String openIdRes = restTemplate.getForObject(openIdUrl, String.class);
			if (!StringUtils.isBlank(openIdRes)) {
				int i = openIdRes.indexOf("(");
				int j = openIdRes.indexOf(")");
				openIdRes = openIdRes.substring(i + 1, j);
				JSONObject openidObj = JSONObject.parseObject(openIdRes);
				map.put("openId",openidObj.getString("openid"));
				return Result.success(openidObj.getString("openid"));
			}
		}
		return Result.fail(CodeMessage.THIRD_PARTY_AUTH_ERROR);
	}

	/**
	 * 获取wechat的认证token和用户OpenID
	 *
	 * @param code
	 * @return
	 */
	public final Result<Map<String, String>> getWechatTokenAndOpenid(String code) {
		Map<String, String> map = new HashMap<>();
		// 获取令牌
		String paramUrl = "?grant_type=authorization_code&appid={0}&secret={1}&code={2}";
		paramUrl = MessageFormat.format(paramUrl, appIdWechat, appkeyWechat, code);
		String tokenUrl = accessTokenUrlWechat + paramUrl;
		log.info(" getWechatTokenUrl:" + tokenUrl);
		String tokenRst = restTemplate.getForObject(tokenUrl, String.class);
		log.info(" tokenRes:" + tokenRst);
		if (StringUtils.contains(tokenRst, "access_token")) {
			Map<String, Object> tokenMap = JSON.parseObject(tokenRst, Map.class);
			map.put("access_token", tokenMap.get("access_token").toString());
			map.put("openId", tokenMap.get("openid").toString());
			map.put("expires_in", tokenMap.get("expires_in").toString());
			map.put("refresh_token", tokenMap.get("refresh_token").toString());
			map.put("unionId", tokenMap.get("unionid").toString());
			return Result.success(map);
		}
		return Result.success();
	}


	/**
	 * 获取QQ用户信息
	 *
	 * @param token
	 * @param openid
	 */
	public Map<String, String> getQQInfo(String token, String openid) throws Exception {
		Map<String, String> result = new HashMap<>();
		String param = "?format=json&access_token={0}&oauth_consumer_key={1}&openid={2}";
		param = MessageFormat.format(param, token, appIdQq, openid);
		String url = getUserInfoUrlQq + param;
		String res = restTemplate.getForObject(url, String.class);
		JSONObject json = JSONObject.parseObject(res);
		if (json.getIntValue("ret") == 0) {
			String nickName = json.getString("nickname");
			String img = json.getString("figureurl_qq_2");
			if (img == null || "".equals(img)) {
				img = json.getString("figureurl_qq_1");
			}
			String sex = json.getString("gender");
			result.put("nick_name", nickName);
			result.put("img", img);
			result.put("sex", sex);
		} else {
			throw new IllegalArgumentException(json.getString("msg"));
		}
		return result;
	}

	/**
	 * 获取wechat用户信息
	 *
	 * @param token
	 * @param openid
	 */
	public Map<String, String> getWechatInfo(String token, String openid) throws Exception {
		Map<String, String> map = new HashMap<>();
		String param = "?access_token={0}&openid={1}";
		param = MessageFormat.format(param, token, openid);
		String url = getUserInfoURLWechat + param;
		String result = restTemplate.getForObject(url, String.class);
		JSONObject res = JSONObject.parseObject(result);
		if (res.get("openid") != null) {
			log.info(" getWechatInfo info:" + res.toJSONString());
			String nickName = res.getString("nickname");
			String img = res.getString("headimgurl");
			if (img == null || "".equals(img)) {
				img = res.getString("headimgurl_0");
			}
			String sex = res.getString("sex");
			map.put("nick_name", nickName);
			map.put("img", img);
			map.put("sex", sex);
		} else {
			throw new RuntimeException("获取微信消息失败");
		}
		return map;
	}

	/**
	 * 将格式为s1&s2&s3...的字符串转化成Map集合
	 *
	 * @param str
	 * @return
	 */
	private static final Map<String, String> toMap(String str) {
		Map<String, String> map = new HashMap<>();
		String[] strs = str.split("&");
		for (int i = 0; i < strs.length; i++) {
			String[] ss = strs[i].split("=");
			map.put(ss[0], ss[1]);
		}
		return map;
	}

}
