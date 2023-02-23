package com.gupao.edu.answer.server.entity.res;

import com.gupao.edu.answer.server.entity.AskComments;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description
 * @author: chenlong
 * @data: 2020-03-19 21:39
 **/
@Data
@ApiModel(value = "回答返回实体")
public class AnswerRes {
    @ApiModelProperty(value = "回答id")
    private Integer id;
    @ApiModelProperty(value = "回答用户id")
    private Integer userId;
    @ApiModelProperty(value = "回答内容")
    private String content;
    @ApiModelProperty(value = "点赞数")
    private Integer supports;
    @ApiModelProperty(value = "反对数")
    private Integer oppositions;
    @ApiModelProperty(value = "回复数")
    private Integer comments;
    @ApiModelProperty(value = "评论列表")
    private List<AskComments> commentsList;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "创建时间,0:没有点赞没有踩,1:点赞,2:踩")
    private Byte isSupport;
}
