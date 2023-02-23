package com.gupao.edu.course.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 〈我的作业（详情）〉
 *
 * @author yangshibo
 * @create 2020/3/25
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("作业详情实体")
public class HomeworkPageDetailResp {

    @ApiModelProperty(name = "courseTitle", value = "作业名字")
    private String courseTitle;
    @ApiModelProperty(name = "homeworkContext", value = "作业内容")
    private String homeworkContext;
    @ApiModelProperty(name = "ctime", value = "发布时间")
    private Date ctime;

    //TODO:暂无 浏览量
    @ApiModelProperty(name = "PV", value = "浏览量")
    private Integer PV;

    @ApiModelProperty(name = "homeworkAnswerDTOS", value = "作业回复实体")
    private List<HomeworkReplyDTO> homeworkAnswerDTOS;

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("作业回复实体")
    static class HomeworkReplyDTO {
        @ApiModelProperty(name = "userId", value = "用户id")
        private Integer userId;
        @ApiModelProperty(name = "ctime", value = "发布时间")
        private Date ctime;
        @ApiModelProperty(name = "userName", value = "用户名")
        private String userName;
        @ApiModelProperty(name = "userNumber", value = "学号")
        private String userNumber;
        @ApiModelProperty(name = "isGood", value = "是否好评")
        private Integer isGood;
        @ApiModelProperty(name = "replyContent", value = "回复内容")
        private String replyContent;
        @ApiModelProperty(name = "star", value = "得分")
        private Double star;
        @ApiModelProperty(name = "homeworkCommentDTOS", value = "作业评论实体")
        private List<HomeworkCommentDTO> homeworkCommentDTOS;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel(value = "作业评论实体")
    static class HomeworkCommentDTO {
        @ApiModelProperty(name = "userId", value = "用户id")
        private Integer userId;
        @ApiModelProperty(name = "userName", value = "用户名")
        private String userName;
        @ApiModelProperty(name = "userName", value = "回答实体")
        private String commentContent;
        @ApiModelProperty(name = "isOfficeAnswer", value = "是否官方回复")
        private Integer isOfficeAnswer;
    }
}
