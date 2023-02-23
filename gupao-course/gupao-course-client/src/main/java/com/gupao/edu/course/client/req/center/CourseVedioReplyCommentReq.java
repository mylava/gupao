package com.gupao.edu.course.client.req.center;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 针对提问回答进行评价（可互评）
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-07 12:57
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseVedioReplyCommentReq implements Serializable {

    private static final long serialVersionUID = -6142448706336457810L;
    /**
     * 自关联ID---针对哪一条回复
     */
    @ApiModelProperty(value = "自关联ID", name = "parentId",  position = 1)
    private Integer parentId;

    /**
     * 答疑回复id
     */
    @ApiModelProperty(value = "答疑回复id", name = "courseVideoQuestionReplyId",  position = 2)
    @NotNull(message = "回复id不能为空")
    private Integer courseVideoQuestionReplyId;

    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 3)
    @NotBlank(message = "用户编码不能为空")
    private String userUniqueCode;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容", name = "content",  position = 4)
    @NotBlank(message = "评论内容不能为空")
    private String content;


}
