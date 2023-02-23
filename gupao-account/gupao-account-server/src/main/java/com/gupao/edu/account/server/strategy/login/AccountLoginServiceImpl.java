package com.gupao.edu.account.server.strategy.login;

import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.req.LoginReq;
import com.gupao.edu.account.client.resp.LoginResp;
import com.gupao.edu.account.server.service.UserService;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.common.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Class: 账户（手机号）登录实现
 *
 * @Author: wangzhong
 * @Date: 2020-03-25 21:02
 */
@Service
public class AccountLoginServiceImpl extends AbstractLoginServiceImpl implements LoginService, InitializingBean {
	@Autowired
	private UserService userService;


	@Value("${user.password.salt:123456}")
	private String passwordSalt;

	@Value("${user.jwt.secret:abcd}")
	private String secret;


	@Override
	public Result<LoginResp> login(LoginReq loginReq) {
		//判断前端手机号是否传递
		if (StringUtils.isBlank(loginReq.getMobile())) {
			return Result.fail(CodeMessage.LOGIN_MOBILE_NULL);
		}
		User user = userService.getByMobile(loginReq.getMobile());

		//判断用户是否存在
		if (null == user) {
			return Result.fail(CodeMessage.LOGIN_USER_NOT_EXIST);
		}
		//判断密码是否正确
		if (!Objects.equals(user.getPassword(), Md5Utils.MD5Encode(loginReq.getPassword()))) {
			return Result.fail(CodeMessage.LOGIN_PASSWORD_INCORRECT);
		}

		return newlogin(loginReq,user);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		container.put(LoginType.ACCOUNT_LOGIN.getCode(), this);
	}
}