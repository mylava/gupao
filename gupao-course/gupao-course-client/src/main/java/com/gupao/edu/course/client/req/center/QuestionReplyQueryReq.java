package com.gupao.edu.course.client.req.center;

import com.gupao.edu.course.client.req.BasePageParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 〈〉
 *
 * @create 2020/3/25
 * @since 1.0.0
 */
@Data
@ApiModel
public class QuestionReplyQueryReq extends BasePageParams implements Serializable {

    private static final long serialVersionUID = -5867387889032002185L;

    @ApiModelProperty(name = "问题id",value = "questionId")
    private Integer questionId;
    @ApiModelProperty(name = "排序方式 0 默认 1.最新",value = "desc")
    private Integer desc;
}
