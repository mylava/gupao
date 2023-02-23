package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/3/25 18:32
 * 作业列表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("作业列表")
public class CourseHomeworkResp {

    @ApiModelProperty(value = "作业题目id", name = "homeworkId")
    private Integer homeworkId;

    @ApiModelProperty(value = "课程名字", name = "courseName")
    private String courseName;

    @ApiModelProperty(value = "作业出题时间", name = "createTime")
    private String createTime;

    @ApiModelProperty(value = "章节名字", name = "outlineName")
    private String outlineName;

    @ApiModelProperty(value = "已提交人数", name = "commitNum")
    private Integer commitNum;

//    @ApiModelProperty(value = "用户名字", name = "userName")
//    private String userName;

    @ApiModelProperty(value = "优秀作业数", name = "userName")
    private Integer goodNum;

//    @ApiModelProperty(value = "作业提交时间", name = "commitTime")
//    private Date commitTime;
}
