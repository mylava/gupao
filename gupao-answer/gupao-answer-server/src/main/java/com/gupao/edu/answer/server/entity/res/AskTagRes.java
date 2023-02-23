package com.gupao.edu.answer.server.entity.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * @description
 * @author: chenlong
 * @data: 2020-04-11 20:35
 **/
@Data
@ApiModel(value = "标签返回对象")
public class AskTagRes {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "标签名称")
    private String name;
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;
    @ApiModelProperty(value = "logo")
    private String logo;
    @ApiModelProperty(value = "摘要")
    private String summary;
    @ApiModelProperty(value = "说明")
    private String description;
    @ApiModelProperty(value = "父id")
    private Integer parentId;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
}
