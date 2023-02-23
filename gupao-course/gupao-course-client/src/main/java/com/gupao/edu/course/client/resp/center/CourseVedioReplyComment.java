package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <h3>gupao-parent</h3>
 * <p>course_vedio_reply_comment</p>
 * 答疑 : 回复 评论表 : 评论的评论
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-06 22:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("课程视频回复的评论")
public class CourseVedioReplyComment implements Serializable {
    private static final long serialVersionUID = 8853047139660731512L;

    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id", name = "id",  position = 1)
    private Integer id;
    /**
     * 上级评论 id
     */
    @ApiModelProperty(value = "上级评论id", name = "parentId",  position = 2)
    private Integer parentId;
    /**
     * 是回复下的一级评论 : 如果是评论的评论 : 根据 parentId 去判断
     */
    @ApiModelProperty(value = "回复的直接评论id", name = "courseVedioReplyId",  position = 3)
    private Integer courseVideoReplyId;
    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 4)
    private String userUniqueCode;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", name = "userName",  position = 5)
    private String userName;
    /**
     * 点赞数量
     */
    @ApiModelProperty(value = "点赞数量", name = "praisCount",  position = 6)
    private Integer praiseCount;
    /**
     * byte[] 评论内容
     */
    @ApiModelProperty(value = "评论内容", name = "content",  position = 7)
    private String content;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime",  position = 8)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime",  position = 9)
    private LocalDateTime updateTime;
    /**
     * 根据 parentId = id 去设置所有的子评论
     */
    @ApiModelProperty(value = "评论的评论", name = "commentChildList",  position = 10)
    private List<CourseVedioReplyComment> commentChildList;

}
