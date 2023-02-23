package com.gupao.edu.answer.server.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
@ApiModel("回复评论请求")
public class CommentsQueryReplyReq  {

    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "评论上级编号")
    private Integer sourceId;
    @ApiModelProperty(value = "评论上级类型" , allowableValues = "App\\Models\\Comment")
    private String sourceType;

    @ApiModelProperty("页码")
    private int pageNum;
    @ApiModelProperty("页面展示长度")
    private int pageSize;
    public void formatePage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
    }
    public void requestCheck() {
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
