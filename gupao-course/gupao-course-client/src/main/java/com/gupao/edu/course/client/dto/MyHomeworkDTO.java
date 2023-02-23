package com.gupao.edu.course.client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 我的作业
 */
@Data
@ApiModel("我的作业实体类")
public class MyHomeworkDTO {

    @ApiModelProperty(value = "作业回复主键---ID", name = "id",  position = 1)
    private Integer id;

    @ApiModelProperty(value = "题目ID", name = "courseHomeworkId",  position = 2)
    private Integer courseHomeworkId;

    @ApiModelProperty(value = "课程名字", name = "courseName",  position = 3)
    private String courseName;

    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 4)
    private String userUniqueCode;

    @ApiModelProperty(value = "作业内容", name = "homeworkReplyContent",  position = 5)
    private String homeworkReplyContent;

    @ApiModelProperty(value = "作业得分", name = "score",  position = 6)
    private Integer score;

    @ApiModelProperty(value = "创建时间", name = "createTime",  position = 7)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "大纲ID（大纲章ID，不是节ID）", name = "courseOutlineId",  position = 8)
    private Integer courseOutlineId;

    @ApiModelProperty(value = "大纲名字", name = "courseOutlineName",  position = 9)
    private String courseOutlineName;
}
