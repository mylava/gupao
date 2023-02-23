package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户关注列表参数")
public class AttentionPageReq {

    @ApiModelProperty(value = "唯一编码", name = "userUniqueCode",required = true)
    private String userUniqueCode;
    @ApiModelProperty(value = "页数")
    private Integer pageNum;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    public AttentionPageReq() {
        this.pageNum = 1;
        this.pageSize = 10;
    }
}
