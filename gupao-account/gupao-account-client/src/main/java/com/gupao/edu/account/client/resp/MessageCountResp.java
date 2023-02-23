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
 * @date: 2020/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("我的消息数返回值")
public class MessageCountResp {

    @ApiModelProperty(value = "我的未读消息数", position = 1)
    private Integer unreadNum;
}
