package com.gupao.edu.account.server.strategy.login;

import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.req.LoginReq;
import com.gupao.edu.account.client.resp.LoginResp;
import com.gupao.edu.account.server.service.UserService;
import com.gupao.edu.account.server.utils.ThirdPartyLoginHelper;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class: 微信登录实现
 *
 * @Author: wangzhong
 * @Date: 2020-03-25 20:59
 */
@Service
public class WeChatLoginServiceImpl extends AbstractLoginServiceImpl implements LoginService, InitializingBean {
	@Autowired
	ThirdPartyLoginHelper thirdPartyLoginHelper;
	@Autowired
	private UserService userService;
	@Override
	public Result<LoginResp> login(LoginReq loginReq) {
		if(StringUtils.isBlank(loginReq.getAuthCode())){ Result.fail(CodeMessage.THIRD_PARTY_AUTH_NULL);}
		//获取wechat的认证token和用户OpenID
		String weChatOpenId = thirdPartyLoginHelper.getWechatTokenAndOpenid(loginReq.getAuthCode()).getData().get("openId");
		if (weChatOpenId == null) {
			return Result.fail(CodeMessage.THIRD_PARTY_AUTH_ERROR);
		}
		// 判断是否注册手机号，没注册返回
		User user = userService.getByWeChatOpenId(weChatOpenId);
		//判断用户是否存在
		if (null == user) {
			LoginResp loginResp = new LoginResp();
			loginResp.setHasBindMobile(false);
			loginResp.setAccessToken("");
			return Result.success(loginResp);
		}

		// 注册了登录
		return newlogin(loginReq,user);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		container.put(LoginType.WECHAT_LOGIN.getCode(), this);
	}
}