package com.gupao.edu.account.server.strategy.login;

import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.req.LoginReq;
import com.gupao.edu.account.client.resp.LoginResp;
import com.gupao.edu.account.server.service.CommonService;
import com.gupao.edu.account.server.service.UserService;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.common.utils.Md5Utils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Class: 手机号登录实现
 *
 * @Author: wangzhong
 * @Date: 2020-03-25 21:00
 */
@Service
public class PhoneLoginServiceImpl extends AbstractLoginServiceImpl implements LoginService, InitializingBean {
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;
	@Value("${user.password.salt:123456}")
	private String passwordSalt;

	@Value("${user.jwt.secret:abcd}")
	private String secret;

	/**
	 * 免密登录  只需要手机号与验证码--需要注册账号
	 * @param loginReq
	 * @return
	 */
	@Override
	public Result<LoginResp> login(LoginReq loginReq) {
		Result result = null;
		try {
			result = commonService.verifyCode(loginReq.getMobile(),loginReq.getBizCode(),loginReq.getSms());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result.getData() != null) {
			User user = userService.getByMobile(loginReq.getMobile());
			//判断用户是否存在
			if (null == user) {
				userService.registerUser(loginReq.getMobile(), loginReq.getPassword(),loginReq.getBizCode(),loginReq.getSms());
			}
			return  newlogin(loginReq,user);
		}
		return Result.fail(CodeMessage.VERIFY_CODE_FAIL_CHECK);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		container.put(LoginType.PHONE_LOGIN.getCode(), this);
	}
}