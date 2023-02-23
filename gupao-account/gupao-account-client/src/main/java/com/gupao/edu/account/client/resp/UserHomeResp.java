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
@ApiModel("我的返回值")
public class UserHomeResp {

    @ApiModelProperty(value = "头像", name = "avatar",  position = 1)
    private String avatar;

    @ApiModelProperty(value = "昵称", name = "nickName",  position = 2)
    private String nickName;

    @ApiModelProperty(value = "消息数量", name = "messageCount",  position = 3)
    private Integer messageCount;

    @ApiModelProperty(value = "订单数量", name = "orderCount",  position = 4)
    private Integer orderCount;

    @ApiModelProperty(value = "可用学币数量", name = "accountBalance",  position = 5)
    private Integer accountBalance;

    @ApiModelProperty(value = "收藏数量", name = "favoriteCount",  position = 6)
    private Integer favoriteCount;

    @ApiModelProperty(value = "优惠券数量", name = "couponCount",  position = 7)
    private Integer couponCount;

    @ApiModelProperty(value = "是否为会员", name = "isMember",  position = 8)
    private Boolean isMember;

    @ApiModelProperty(value = "会员剩余天数", name = "memberSurplus",  position = 9)
    private Integer memberSurplus;

    @ApiModelProperty(value = "是否有未领书籍", name = "bookWait",  position = 10)
    private Boolean bookWait;

    @ApiModelProperty(value = "是否有学籍", name = "hasSchollRoll",  position = 11)
    private Boolean hasSchollRoll;
}

