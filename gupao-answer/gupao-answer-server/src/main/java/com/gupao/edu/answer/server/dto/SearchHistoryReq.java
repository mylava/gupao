package com.gupao.edu.answer.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("搜索历史请求参数")
public class SearchHistoryReq {
    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("第几页")
    private Integer pageNum;
    @ApiModelProperty("请求条数")
    private Integer pageSize;
}
