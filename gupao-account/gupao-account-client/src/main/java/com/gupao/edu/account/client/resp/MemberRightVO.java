package com.gupao.edu.account.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 会员权益VO
 * </p>
 *
 * @author wuzhenping
 * @since 2020-03-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("会员权益项")
public class MemberRightVO {

    @ApiModelProperty(value = "关联[会员权益字典]的权益ID", name = "rightName")
    private Integer memberRightId;

    @ApiModelProperty(value = "权益名称（字典数据名称）", name = "rightName")
    private String dictName;

    @ApiModelProperty(value = "权益图片（描述）", name = "description")
    private String description;

    //（20200427先空着）
    @ApiModelProperty(value = "权益图片（待用）", name = "imgUrl")
    private String imgUrl;

    @ApiModelProperty(value = "权益项包含资源列表", name = "memberRightResourceVOList")
    List<MemberRightResourceVO> memberRightResourceVOList;
}
