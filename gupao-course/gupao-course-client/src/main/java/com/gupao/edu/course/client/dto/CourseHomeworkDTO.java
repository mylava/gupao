package com.gupao.edu.course.client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 作业信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("作业信息实体类")
public class CourseHomeworkDTO {

    @ApiModelProperty(value = "作业题目id", name = "homeworkId")
    private Integer homeworkId;

    @ApiModelProperty(value = "课程名字", name = "courseName")
    private String courseName;

    @ApiModelProperty(value = "作业出题时间", name = "createTime")
    private String createTime;

    @ApiModelProperty(value = "章节名字", name = "outlineName")
    private String outlineName;
}
