package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/21 10:27
 * 我的作业列表
 */
@Data
@ApiModel("作业列表实体类")
public class MyHomeworkResp {
    /**
     * 作业回复主键---ID
     */
    @ApiModelProperty(value = "作业回复主键---ID", name = "id",  position = 1)
    private Integer id;

    /**
     * 作业id
     */
    @ApiModelProperty(value = "题目ID", name = "courseHomeworkId",  position = 2)
    private Integer courseHomeworkId;
    /**
     * 课程名字
     */
    @ApiModelProperty(value = "课程名字", name = "courseName",  position = 3)
    private String courseName;

    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 4)
    private String userUniqueCode;

    /**
     * 作业内容
     */
    @ApiModelProperty(value = "作业内容", name = "homeworkReplyContent",  position = 5)
    private String homeworkReplyContent;

    /**
     * 作业得分
     */
    @ApiModelProperty(value = "作业得分", name = "score",  position = 6)
    private Integer score;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime",  position = 7)
    private LocalDateTime createTime;

    /**
     * 大纲ID（大纲章ID，不是节ID）
     */
    @ApiModelProperty(value = "大纲ID（大纲章ID，不是节ID）", name = "courseOutlineId",  position = 8)
    private Integer courseOutlineId;
    /**
     * 大纲名字
     */
    @ApiModelProperty(value = "大纲名字", name = "courseOutlineName",  position = 9)
    private Integer courseOutlineName;
    /**
     * 评论数
     */
    @ApiModelProperty(value = "评论数", name = "commentNum",  position = 10)
    private Integer commentNum;
}
