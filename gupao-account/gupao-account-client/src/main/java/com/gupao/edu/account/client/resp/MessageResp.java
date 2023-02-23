package com.gupao.edu.account.client.resp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("平台通知返回值")
public class MessageResp {

    @ApiModelProperty(value = "平台通知未读数量", name = "systemCount",  position = 1)
    private Integer systemCount;

    @ApiModelProperty(value = "互动消息未读数量", name = "interactionCount",  position = 2)
    private Integer interactionCount;

    @ApiModelProperty(value = "私信未读数量", name = "letterCount",  position = 3)
    private Integer letterCount;

    @ApiModelProperty(value = "平台通知列表", name = "systemAttentionList",  position = 4)
    private List<SystemAttentionVO> systemAttentionList;

    @ApiModelProperty(value = "互动消息列表", name = "userInteractionVOList",  position = 5)
    private Page<UserInteractionVO> userInteractionVOList;

    @ApiModelProperty(value = "私信列表", name = "personalLetterVOList",  position = 6)
    private List<PersonalLetterVO> personalLetterVOList;

}

