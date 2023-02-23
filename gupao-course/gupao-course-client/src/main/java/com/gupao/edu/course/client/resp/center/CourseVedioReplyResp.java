package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <h3>gupao-parent</h3>
 * <p>course_vedio_reply</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-06 21:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("问题回复实体")
public class CourseVedioReplyResp implements Serializable {


    private static final long serialVersionUID = -6113795271410014567L;

    @ApiModelProperty(value = "回复id", name = "id",  position = 1)
    private Integer id;
    /**
     * 回复的问题标题
     */
    @ApiModelProperty(value = "回复的问题标题", name = "questionTitle",  position = 2)
    private String questionTitle;
    /**
     * 回复的问题id
     */
    @ApiModelProperty(value = "回复的问题id", name = "courseVedioQuestionId",  position = 3)
    private Integer courseVideoQuestionId;
    /**
     * 回复的用户编码
     */
    @ApiModelProperty(value = "回复的用户编码", name = "userUniqueCode",  position = 4)
    private String userUniqueCode;
    /**
     *回复用户名称
     */
    @ApiModelProperty(value = "回复用户名称", name = "userName",  position = 5)
    private String userName;
    /**
     *点赞数量
     */
    @ApiModelProperty(value = "点赞数量", name = "praiseCount",  position = 6)
    private Integer praiseCount;
    /**
     * 反对数量
     */
    @ApiModelProperty(value = "反对数量", name = "negativeCount",  position = 7)
    private Integer negativeCount;

    /**
     * 回复内容 blob
     * byte[]
     */
    @ApiModelProperty(value = "回复内容", name = "content",  position = 9)
    private String content;
    /**
     * 是否被采纳
     */
    @ApiModelProperty(value = "是否被采纳", name = "isAdopted",  position = 10)
    private Integer isAdopted;
    /**
     * 最终 final  安全的 采纳时间
     */
    @ApiModelProperty(value = "采纳时间", name = "adoptedTime",  position = 11)
    private LocalDateTime adoptedTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime",  position = 12)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", name = "updateTime",  position = 13)
    private LocalDateTime updateTime;

    /**
     *  回复 下的 所有评论 : 评论的评论
     */
    @ApiModelProperty(value = "课程视频回复的评论集合", name = "courseVedioReplyCommentList",  position = 14)
    private List<CourseVedioReplyComment> courseVedioReplyCommentList;
}
