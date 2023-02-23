package com.gupao.edu.answer.server.entity.req;

import com.gupao.edu.answer.server.enums.SourceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CommentsReq  {
    @ApiModelProperty(value = "评论上级编号")
    private Integer sourceId;
    @ApiModelProperty(value = "评论上级类型" , allowableValues = "App\\Models\\Article")
    private String sourceType;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "排序规则",allowableValues = "default , new")
    private String orderBy = "default";

    @ApiModelProperty("页码")
    private int pageNum;
    @ApiModelProperty("页面展示长度")
    private int pageSize;

    public void formatePage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
    }
    public void requestCheck() {
        SourceType sourceTypeByCode = SourceType.getSourceTypeByCode(sourceType);
        if(null == sourceTypeByCode){

            throw new RuntimeException("SourceType 不匹配");
        }
    }

}
