package com.gupao.edu.answer.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 课程
 */
@Data
@ApiModel("全局搜索返回课程数据")
public class CourseDTO {

    @ApiModelProperty(value = "课程Id", name = "courseId", position = 1)
    private Integer courseId;

    /**
     * 课程类型id（训练营、精品小课、vip课）
     */
    @ApiModelProperty(value = "课程类型id", name = "typeId", position = 2)
    private Integer typeId;

    /**
     * 所属科目： JAVA  大数据
     */
    @ApiModelProperty(value = "所属科目Id", name = "subjectId", position = 3)
    private Integer subjectId;

    /**
     * 课程编码CODE
     */
    @ApiModelProperty(value = "课程编码", name = "courseCode", position = 4)
    private String courseCode;

    /**
     * 课程名
     */
    @ApiModelProperty(value = "课程名字", name = "courseName", position = 5)
    private String courseName;

    /**
     * 课程主题名称
     */
    @ApiModelProperty(value = "课程主题名称", name = "courseTitle", position = 6)
    private String courseTitle;

    /**
     * 课程封面图片
     */
    @ApiModelProperty(value = "课程封面图片", name = "image", position = 7)
    private String image;

    /**
     * 观看人数
     */
    @ApiModelProperty(value = "观看人数", name = "viewNum", position = 8)
    private Integer viewNum;

    /**
     * 已学人数
     */
    @ApiModelProperty(value = "已学人数", name = "studyNum", position = 9)
    private Integer studyNum;

    /**
     * 评论人数
     */
    @ApiModelProperty(value = "评论人数", name = "commentNum", position = 10)
    private Integer commentNum;

    /**
     * 关注人数
     */
    @ApiModelProperty(value = "关注人数", name = "attentionNum", position = 11)
    private Integer attentionNum;

    /**
     * 好评数
     */
    @ApiModelProperty(value = "好评数", name = "goodNum", position = 12)
    private Integer goodNum;

    /**
     * 好评度
     */
    @ApiModelProperty(value = "好评度", name = "goodPraise", position = 13)
    private BigDecimal goodPraise;

    /**
     * 现价，有效价格
     */
    @ApiModelProperty(value = "现价，有效价格", name = "nowPrice", position = 14)
    private BigDecimal nowPrice;

    /**
     * 原价，无效价格
     */
    @ApiModelProperty(value = "原价，无效价格", name = "oldPrice", position = 15)
    private BigDecimal oldPrice;
}
