package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description
 * @author: chenlong
 * @data: 2020-05-04 21:19
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionReplyPraiseReq implements Serializable {

    private static final long serialVersionUID = 5211058100352816820L;
    /**
     * 回答id
     */
    @ApiModelProperty(value = "回答id", name = "courseVideoQuestionReplyId",  position = 1)
    @NotNull(message = "回答id不能为空")
    private Integer courseVideoQuestionReplyId;
    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 2)
    @NotBlank(message = "用户编码不能为空")
    private String userUniqueCode;
    /**
     * 赞和踩状态
     */
    @ApiModelProperty(value = "赞和踩状态,0赞,1踩", name = "type",  position = 3)
    @NotNull(message = "赞和踩状态不能为空")
    private Integer type;

    /**
     * 是否取消赞或踩
     */
    @ApiModelProperty(value = "是否取消赞或踩,0赞/踩,1取消", name = "type",  position = 4)
    @NotNull(message = "是否取消赞或踩状态不能为空")
    private Integer isCancel;
}
