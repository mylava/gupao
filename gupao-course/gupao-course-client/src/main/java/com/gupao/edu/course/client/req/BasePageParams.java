package com.gupao.edu.course.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ichu 分页参数
 * @author hongdu
 * @since 2020-03-20
 */
@Data
@AllArgsConstructor
public class BasePageParams implements Serializable {

    @ApiModelProperty(value = "每页数量")
    @NotNull(message = "每页数量不能为空")
    @Min(1)
    private Integer pageSize;

    @ApiModelProperty(value = "第几页")
    @NotNull(message = "第几页不能为空")
    @Min(1)
    private Integer pageNum;
    /**
     * 0 代表分页
     * 1.代表不分页
     */
    private Boolean isPage;

    public BasePageParams() {
        this.pageSize = 1;
        this.pageNum = 10;
    }
}
