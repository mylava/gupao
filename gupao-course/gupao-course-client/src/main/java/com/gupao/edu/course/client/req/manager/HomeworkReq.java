package com.gupao.edu.course.client.req.manager;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/3/25 18:57
 * 老师题目管理查询实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("作业查询实体")
public class HomeworkReq {
    /**
     * 应该用枚举类--学科名字
     */
    @ApiModelProperty(value = "学科名字", name = "subjectName")
    private String subjectName;
    /**
     * 应该用枚举类--课程名字
     */
    @ApiModelProperty(value = "课程名字", name = "courseName")
    private String courseName;
    /**
     * 应该用枚举类--目录名字
     */
    @ApiModelProperty(value = "目录名字", name = "outlineName")
    private String outlineName;
    /**
     * 作业标题
     */
    @ApiModelProperty(value = "作业标题", name = "homeworkName")
    private String homeworkName;



}
