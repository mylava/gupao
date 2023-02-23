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
@ApiModel("会员权益项资源")
public class MemberRightResourceVO {

    @ApiModelProperty(value = "会员权益资源id", name = "memberRightResourceId")
    private Integer memberRightResourceId;

    @ApiModelProperty(value = "关联其他资源ID,比如优惠券模板ID", name = "resourceId")
    private Integer resourceId;

    @ApiModelProperty(value = "资源名称", name = "name")
    private String name;

    @ApiModelProperty(value = "资源简介", name = "summary")
    private String summary;

    @ApiModelProperty(value = "资源图文描述", name = "description")
    private String description;



}
