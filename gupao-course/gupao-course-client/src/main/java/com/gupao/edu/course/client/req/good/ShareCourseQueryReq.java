package com.gupao.edu.course.client.req.good;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 〈分享课程查询实体〉
 *
 * @author yangshibo
 * @create 2020/3/25
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareCourseQueryReq implements Serializable{
    private static final long serialVersionUID = 6471775783585979567L;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id", name = "courseId")
    @NotNull(message = "课程id不能为空")
    private Integer courseId;

    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode")
    @NotBlank(message = "用户唯一编码不能为空")
    private String userUniqueCode;

    @ApiModelProperty(value = "分享类型", name = "channel")
    @NotNull(message = "分享渠道不能为空")
    private Integer channel;

    @ApiModelProperty(value = "分享感言", name = "message")
    private Integer message;

}
