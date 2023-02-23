package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/21 11:36
 */
@Data
@ApiModel("学员作业评论")
public class HomeworkCommentReq {

    /**
     * 自关联ID---针对哪一条回复
     */
    @ApiModelProperty(value = "学员答作业下评论评论的ID（学员评论作业时传null）", name = "parentId")
    private Integer parentId;

    /**
     * 学员作业id
     */
    @ApiModelProperty(name = "学员作业id",value = "courseHomeworkReplyId")
    private Integer courseHomeworkReplyId;

    /**
     * 用户唯一编码
     */
    @ApiModelProperty(name = "用户唯一编码",value = "userUniqueCode")
    private String userUniqueCode;


    /**
     * 评论内容
     */
    @ApiModelProperty(name = "评论内容",value = "content")
    private String content;





}
