package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 〈修改作业实体〉
 *
 * @author yangshibo
 * @create 2020/3/26
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateMyHomeworkReq {

    @ApiModelProperty(name = "userUniqueCode",value = "用户唯一编码")
    private String userUniqueCode;

    @ApiModelProperty(name = "homeworkId",value = "作业ID")
    private Integer homeworkId;

    @ApiModelProperty(name = "homeworkContent",value = "要修改的作业内容")
    private String homeworkContent;
}
