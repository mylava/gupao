package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class: 登录入参
 *
 * @Author: wangzhong
 * @Date: 2020-03-20 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {

	@ApiModelProperty(value = "手机号", required = false)
	private String mobile;

	@ApiModelProperty(value = "登录密码(密码需md5加密)", required = false)
	private String password;

	@ApiModelProperty(value = "登录类型: 0手机登录 1免密登录(手机验证码登录）2微信登录 3qq登录", required = true)
	private Integer loginType;

	@ApiModelProperty(value = "免密登录验证码", required = false)
	private String sms;

	@ApiModelProperty(value = "业务编码:1-用户注册 2手机号更绑 3-更换密码 4-其他", required = false)
	private Integer bizCode;

	@ApiModelProperty(value = "微信或qq登录的授权账号", required = false)
	private String authCode;


}