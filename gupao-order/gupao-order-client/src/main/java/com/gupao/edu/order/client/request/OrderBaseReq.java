package com.gupao.edu.order.client.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-03-25 20:12
 */
@Data
public class OrderBaseReq {

    @ApiModelProperty(value="当前页", name="currentPage",example = "1", required = false)
    public Integer currentPage = 1; // 当前页
    @ApiModelProperty(value="页大小", name="pageSize",example = "25", required = false)
    protected Integer pageSize = 25; // 分页大小
}
