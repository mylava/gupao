package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/20 18:17
 * 我的课程列表--包含课程进度
 */
@Data
@ApiModel("我的课程实体")
public class MyCourseResp {
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id", name = "courseId",  position = 1)
    private Integer courseId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", name = "courseName",  position = 2)
    private String courseName;
    /**
     * 课程封面图片
     */
    @ApiModelProperty(value = "课程封面图片", name = "imageUrl",  position = 3)
    private String imageUrl;
    /**
     * 学习进度
     */
    @ApiModelProperty(value = "学习进度", name = "rateOfLearning",  position = 4)
    private String rateOfLearning ;
    /**
     * 上次学到的大纲id
     */
    @ApiModelProperty(value = "上次学到的大纲id", name = "outLineId",  position = 5)
    private Integer courseOutLineId;
    /**
     * 大纲名字
     */
    @ApiModelProperty(value = "大纲名字", name = "outLineName",  position = 6)
    private String courseOutLineName;

    /**
     * 以下两个字段 计算进度用
     */
    @ApiModelProperty(value = "播放时长", name = "playSeconds",  position = 7)
    private Long playSeconds;

    @ApiModelProperty(value = "总时长", name = "videoSeconds",  position = 8)
    private Long videoSeconds;


}
