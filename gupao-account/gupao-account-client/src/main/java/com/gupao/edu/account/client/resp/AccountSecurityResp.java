package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
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
@ApiModel("账号绑定页面返回参数")
public class AccountSecurityResp {

    @ApiModelProperty(value = "手机号", position = 1)
    private String mobile;

    @ApiModelProperty(value = "是否已绑定微信", position = 2)
    private Boolean wechatHasBinded;

    @ApiModelProperty(value = "是否已绑定QQ", position = 3)
    private Boolean qqHasBinded;
}
