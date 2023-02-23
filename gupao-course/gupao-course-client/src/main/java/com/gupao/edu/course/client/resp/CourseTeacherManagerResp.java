package com.gupao.edu.course.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/3/25 19:24
 * 老师题目管理返回实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("作业列表实体类")
public class CourseTeacherManagerResp {
    /**
     * 学科ID
     */
    @ApiModelProperty(value = "学科ID", name = "subjectId")
    private Integer subjectId;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", name = "courseId")
    private Integer courseId;
    /**
     * 作业标题
     */
    @ApiModelProperty(value = "作业标题", name = "homework_title")
    private String homework_title;
    /**
     * 作业内容
     */
    @ApiModelProperty(value = "作业内容", name = "homework_context")
    private String homework_context;
    /**
     * 出题人
     */
    @ApiModelProperty(value = "出题人", name = "topicMake")
    private String topicMake;
    /**
     * 布置作业人数
     */
    @ApiModelProperty(value = "布置作业人数", name = "homework_num")
    private Integer homework_num;
    /**
     * 提交作业人数
     */
    @ApiModelProperty(value = "提交作业人数", name = "commit_num")
    private Integer commit_num;
    /**
     * 作业提交率
     */
    @ApiModelProperty(value = "作业提交率", name = "commit_rate")
    private String commit_rate;
    /**
     * 已批改作业人数
     */
    @ApiModelProperty(value = "已批改作业人数", name = "reply_num")
    private Integer reply_num;
    /**
     * 未批改作业人数
     */
    @ApiModelProperty(value = "未批改作业人数", name = "unreply_num")
    private Integer unreply_num;
    /**
     * 作业批改率
     */
    @ApiModelProperty(value = "作业批改率", name = "reply_rate")
    private String reply_rate;
    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间", name = "ctime")
    private Date ctime;

}
