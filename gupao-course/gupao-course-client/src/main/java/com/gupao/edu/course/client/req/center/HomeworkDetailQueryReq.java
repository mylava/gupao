package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 〈〉
 *
 * @create 2020/3/25
 * @since 1.0.0
 */
@Data
@ApiModel
public class HomeworkDetailQueryReq {

    @ApiModelProperty(name = "作业id",value = "homeworkId")
    private Integer homeworkId;

    @ApiModelProperty(name = "userUniqueCode",value = "用户唯一编码")
    private String userUniqueCode;

    @ApiModelProperty(name = "排序方式 0 默认 1.最新",value = "desc")
    private Integer desc;

    @ApiModelProperty(value = "页数")
    private Integer pageNum;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    public HomeworkDetailQueryReq() {
        this.pageNum = 1;
        this.pageSize = 10;
    }
}
