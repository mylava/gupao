package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 解绑第三方账号请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnbindThirdReq {

	@ApiModelProperty(value = "账号来源 1.QQ 2.微信", required = true)
	private Integer bindChannel;

	@ApiModelProperty(value = "用户唯一编码", required = true)
	private String userUniqueCode;
}