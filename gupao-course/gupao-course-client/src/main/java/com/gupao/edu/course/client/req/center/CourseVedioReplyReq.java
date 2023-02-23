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
 * <h3>gupao-parent</h3>
 * <p>CourseVedioReply</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-07 13:01
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "course_vedio_reply")
public class CourseVedioReplyReq  implements Serializable {


    private static final long serialVersionUID = -6113795271410014567L;


    /**
     * 问题id
     */
    @ApiModelProperty(value = "问题id", name = "courseVideoQuestionId",  position = 2)
    @NotNull(message = "问题id不能为空")
    private Integer courseVideoQuestionId;

    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 3)
    @NotBlank(message = "用户唯一编码不能为空")
    private String userUniqueCode;


    /**
     * 回复内容
     */
    @ApiModelProperty(value = "回复内容", name = "content",  position = 4)
    @NotBlank(message = "回复内容不能为空")
    private String content;




}
