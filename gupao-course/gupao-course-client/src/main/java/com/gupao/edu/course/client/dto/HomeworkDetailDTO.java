package com.gupao.edu.course.client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * 作业详情
 * @create 2020/3/25
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("作业详情返回实体")
public class HomeworkDetailDTO {

    @ApiModelProperty(name = "homeworkTitle", value = "作业标题")
    private String homeworkTitle;
    @ApiModelProperty(name = "courseOutLineName", value = "大纲名字")
    private String courseOutLineName;
    @ApiModelProperty(name = "homeworkContext", value = "作业内容")
    private String homeworkContext;
    @ApiModelProperty(name = "createTime", value = "作业发布时间")
    private Date createTime;

}
