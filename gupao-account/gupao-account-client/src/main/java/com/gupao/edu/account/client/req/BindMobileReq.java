package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindMobileReq {

    @ApiModelProperty(value = "绑定账号来源 1.QQ 2.微信", required = true)
    private Integer bindChannel;

    @ApiModelProperty(value = "授权码", required = true)
    private String authCode;

    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "验证码", required = true)
    private String sms;

    @ApiModelProperty(value = "业务编码:1-用户注册 2手机号更绑 3-更换密码 4-其他", required = false)
    private Integer bizCode;

}
