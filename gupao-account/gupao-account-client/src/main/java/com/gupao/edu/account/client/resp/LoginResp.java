package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:
 *
 * @Author: wangzhong
 * @Date: 2020-03-31 14:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录返回值")
public class LoginResp {
	@ApiModelProperty(value = "用户登录token", name = "accessToken",  position = 1)
	private String accessToken;

	@ApiModelProperty(value = "用户是否绑定手机号", position = 2)
	private Boolean hasBindMobile;
}