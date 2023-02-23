package com.gupao.edu.course.client.resp.good;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 〈〉
 *
 * @author yangshibo
 * @create 2020/3/25
 * @since 1.0.0
 */
@ApiModel("分享课程实体")
@Data
@EqualsAndHashCode(callSuper = false)
public class ShareCourseResp {

    @ApiModelProperty(name = "description", value = "课程图文详情")
    private String description;

    @ApiModelProperty(name = "studyNum", value = "学习人数")
    private Integer studyNum;

    @ApiModelProperty(name = "commentNum", value = "评论人数")
    private Integer commentNum;

    @ApiModelProperty(name = "goodPraise", value = "好评率")
    private Double goodPraise;

    @ApiModelProperty(name = "nowPrice", value = "现价")
    private Double nowPrice;

    @ApiModelProperty(name = "oldPrice", value = "原价")
    private Double oldPrice;

    /**
     * 分享 成功后 有交易成功的话 就给我充值20沽泡币
     * todo 确认 分享的课程 关联的商品id  然后完成的交易 是因为我的分享?
     * todo 添加feign接口 调用 暂时写死
     */
    @ApiModelProperty(name = "goodsId", value = "商品id")
    private Integer goodsId;
}
