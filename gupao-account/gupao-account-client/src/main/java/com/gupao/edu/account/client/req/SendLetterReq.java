package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 14/04/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendLetterReq {

    @ApiModelProperty(value = "我的用户编码", required = true, position = 1)
    @NotBlank(message = "我的用户唯一编码不能为空(接收方)")
    private String userUniqueCode;

    @ApiModelProperty(value = "对方的用户编码", required = true, position = 2)
    @NotBlank(message = "对方用户唯一编码不能为空(发送方)")
    private String oppositeUniqueCode;

    @ApiModelProperty(value = "消息内容", name = "message",  position = 3)
    @NotBlank(message = "消息内容不能为空")
    private String message;

    @ApiModelProperty(value = "是否为图片", name = "isImage",  position = 4)
    private Boolean isImage;

    @ApiModelProperty(value = "是否主动发起的第一条私信: true 是 false 是", name = "isFirstNews",  position = 5)
    private Boolean isFirstNews;

    //******** 阅读 时新增一个阅读状态 todo 暂时留着
//    @ApiModelProperty(value = "是否已读: 0 未读 1 已读", name = "isRead",  position = 6)
//    private int isRead;

    //*********** 新增 入参 id (在查询的时候返回前端) 然后更新的时候前端传入后台进行更新
//    @ApiModelProperty(value = "记录id", name = "isRead",  position = 7)
//    private Integer id;
}
