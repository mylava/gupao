package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Class:
 *
 * @Author: wangzhong
 * @Date: 2020-03-20 16:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindThirdReq {

	@ApiModelProperty(value = "绑定账号来源 1.QQ 2.微信", required = true)
	@Max(2)@Min(1)
	private Integer bindChannel;

	@ApiModelProperty(value = "授权码", required = true)
	@NotBlank(message = "授权码不能为空")
	private String authCode;

	@ApiModelProperty(value = "用户唯一编码", required = true)
	private String userUniqueCode;
}