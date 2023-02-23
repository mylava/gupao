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
public class ChangeMobileReq {

    @ApiModelProperty(value = "用户唯一编码", required = true)
    private String userUniqueCode;

    @ApiModelProperty(value = "原手机号", required = true)
    private String originMobile;

    @ApiModelProperty(value = "原手机号短信", required = true)
    private String originSms;

    @ApiModelProperty(value = "新的手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "新的手机号短信", required = true)
    private String sms;

    @ApiModelProperty(value = "业务编码:1-用户注册 2手机号更绑 3-更换密码 4-其他", required = false)
    private Integer bizCode;
}
