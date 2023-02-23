package com.gupao.edu.answer.server.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;
@Data
@ApiModel("新曾修改评论请求")
public class CommentsChangeReq  {
    @ApiModelProperty(value = "评论id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "评论上级编号")
    private Integer sourceId;
    @ApiModelProperty(value = "评论上级类型" ,allowableValues = "App\\Models\\Comment | App\\Models\\Article")
    private String sourceType;
    @ApiModelProperty(value = "操作类型 " , allowableValues = "add ,update ")
    private String type;
    public void requestCheck() {
        if( StringUtils.isEmpty(type)){
            throw new RuntimeException( "操作类型type不能为空！");
        }
        if(StringUtils.isEmpty(content)){
            throw new RuntimeException( "评论内容不能为空！");
        }
        if(StringUtils.isEmpty(userId)){
            throw new RuntimeException("评论用户id不能为空！");
        }
        if(StringUtils.isEmpty(sourceId)){
            throw new RuntimeException( "sourceId不能为空！");
        }
        if(StringUtils.isEmpty(sourceType)){
            throw new RuntimeException("sourceType不能为空！");
        }
    }
}
