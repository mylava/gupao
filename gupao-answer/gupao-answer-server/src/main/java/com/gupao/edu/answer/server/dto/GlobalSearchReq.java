package com.gupao.edu.answer.server.dto;

import com.gupao.edu.common.constant.SearchEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("全局搜索")
public class GlobalSearchReq {
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("搜索关键词")
    @NotEmpty(message = "搜索词不能为空")
    private String keywords;
    @ApiModelProperty("搜索类型 0：综合 1：课程 2：问答 3：文章 4：用户")
    private SearchEnums.SearchType type;
    @ApiModelProperty("第几页")
    private Integer current = 1;
    @ApiModelProperty("每页条数")
    private Integer size = 20;
}
