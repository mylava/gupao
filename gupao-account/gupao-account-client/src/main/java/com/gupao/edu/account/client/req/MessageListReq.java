package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 2020/4/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageListReq extends BasePageReq {

    @ApiModelProperty(value = "消息类型：1平台通知 2互动提醒(已取消) 3私信", required = true)
    @NotNull(message = "消息类型不能为空")
    @Min(1)@Max(3)
    private Integer messageType;
}
