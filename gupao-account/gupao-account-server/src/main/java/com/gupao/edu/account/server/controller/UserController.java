package com.gupao.edu.account.server.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.req.*;
import com.gupao.edu.account.client.resp.*;
import com.gupao.edu.account.server.service.CommonService;
import com.gupao.edu.account.server.service.UserService;
import com.gupao.edu.account.server.strategy.login.LoginService;
import com.gupao.edu.account.server.utils.PhoneNumberUtil;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author wuzhenping
 * @since 2020-03-18
 */
@Api(tags = {"用户账号相关接口"})
@RestController
@RequestMapping("/account")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "1.用户注册", notes = "用户注册")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String"),
			@ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String"),
			@ApiImplicitParam(name = "bizCode", value = "业务编码:1-用户注册 2手机号更绑 3-更换密码 4-其他",
					required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "sms", value = "验证码", required = true, dataType = "String")
	})
	@PostMapping(value = "/register")
	public Result<LoginResp> register(@RequestParam(value = "mobile") String mobile,
									  @RequestParam(value = "password") String password,
									  @RequestParam(value = "bizCode", required = false) Integer bizCode,
									  @RequestParam(value = "sms") String sms) {

		// 1.执行注册逻辑 2.直接进行登录操作，返回登录后的信息
		if(StringUtils.isEmpty(mobile)){
			return Result.fail(CodeMessage.LOGIN_MOBILE_NULL);
		}
		if (!PhoneNumberUtil.isMobileNO(mobile)){
			return Result.fail(CodeMessage.MOBILE_INCORRECT);
		}
		Result result = userService.registerUser(mobile, password,bizCode,sms);
		return result;
	}

	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "2.用户登录", notes = "用户登录")
	@ApiImplicitParam(name = "loginReq", value = "登录请求参数", required = true, dataType = "LoginReq")
	@PostMapping(value = "/login")
	public Result<LoginResp> login(@RequestBody LoginReq loginReq) {
		// 1.创建登录类型枚举类 2.根据登录类型执行登录逻辑 3.如果是免密登录，可能需要创建账号
		LoginService loginService = LoginService.container.get(loginReq.getLoginType());
		Result<LoginResp> result = loginService.login(loginReq);
		return result;
	}

	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "3.绑定手机号", notes = "绑定手机号")
	@ApiImplicitParam(name = "bindMobileReq", value = "绑定手机号请求参数", required = true, dataType = "BindMobileReq")
	@PostMapping(value = "/bindMobile")
	public Result<LoginResp> bindMobile(@RequestBody BindMobileReq bindMobileReq) throws Exception {
		//TODO 1.验证短信是否正确、超时  2.重新验证第三方登录后，执行注册逻辑，最后执行登录逻辑
		Result<LoginResp> result = userService.bindMobile(bindMobileReq);
		return result;
	}

	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "4.跳转账号安全页面", notes = "跳转账号安全页面")
	@ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
	@PostMapping(value = "/security")
	public Result<AccountSecurityResp> security(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
		//TODO 1.查询手机号码，返回给客户端
		Result<AccountSecurityResp> result = userService.security(userUniqueCode);
		return result;
	}

	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "5.更绑手机号", notes = "更绑手机号")
	@ApiImplicitParam(name = "changeMobileReq", value = "更绑手机号请求参数", required = true, dataType = "ChangeMobileReq")
	@PostMapping(value = "/changeMobile")
	public Result changeMobile(@RequestBody ChangeMobileReq changeMobileReq) throws Exception {
		//TODO 更绑手机号流程：1.客户端请求发送短信给旧手机号 2.验证旧手机短信是否正确
		//					3.客户端请求发送短信给新手机 4.验证新手机短信是否正确 5.保存新手机号码到DB
		Result result = userService.changeMobile(changeMobileReq);
		return result;
	}

	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "6.用户修改密码", notes = "用户修改密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String"),
			@ApiImplicitParam(name = "originPassword", value = "旧密码", required = true, dataType = "String"),
			@ApiImplicitParam(name = "password", value = "新密码", required = true, dataType = "String")
	})
	@PostMapping(value = "/modifyPassword")
	public Result modifyPassword(@RequestParam(value = "userUniqueCode") String userUniqueCode,
										  @RequestParam(value = "originPassword") String originPassword,
										  @RequestParam(value = "password") String password) {
		Result result = userService.modifyPassword(userUniqueCode, originPassword, password);
		return result;
	}

	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "7.绑定第三方账号", notes = "绑定第三方账号")
	@PostMapping(value = "/bindThird")
	public Result bindThird(@Valid @RequestBody BindThirdReq bindThirdReq) {
		return userService.bindThird(bindThirdReq);
	}

	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "8.解绑第三方账号", notes = "解绑第三方账号")
	@PostMapping(value = "/unBindThird")
	public Result unBindThird(@RequestBody UnbindThirdReq unbindThirdReq) {
		return userService.unBindThird(unbindThirdReq);
	}

	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "9.账户退出", notes = "账户退出")
	@ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
	@PostMapping(value = "/loginOut")
	public Result loginOut( @NotBlank(message = "userUniqueCode不能为空") @RequestParam(value = "userUniqueCode") String userUniqueCode) {
		return userService.loginOut(userUniqueCode);
	}

	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "10.我的消息数角标", notes = "我的消息数角标")
	@ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
	@PostMapping(value = "/messageCount")
	public Result<MessageCountResp> messageCount(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
		return userService.messageCount(userUniqueCode);
	}
}
