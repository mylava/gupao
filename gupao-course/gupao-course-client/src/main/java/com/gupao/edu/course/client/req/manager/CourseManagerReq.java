package com.gupao.edu.course.client.req.manager;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/3/25 20:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("学生作业管理查询实体类")
public class CourseManagerReq {

    /**
     * 学科名字
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
     * 应该用枚举类--章节名字
     */
    @ApiModelProperty(value = "章节名字", name = "videoName")
    private String videoName;
    /**
     * 作业标题
     */
    @ApiModelProperty(value = "作业标题", name = "homeworkName")
    private String homeworkName;
    /**
     * 是否优秀作业
     */
    @ApiModelProperty(value = "是否优秀作业", name = "isGood")
    private Boolean isGood;
    /**
     * 得分等级
     */
    @ApiModelProperty(value = "得分等级", name = "gradeType")
    private Boolean gradeType;

    /**
     * 批阅老师ID
     */
    @ApiModelProperty(value = "批阅老师ID", name = "replyTeacherid")
    private Integer replyTeacherid;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
}
