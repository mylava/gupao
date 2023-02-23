package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <h3>gupao-parent</h3>
 * <p>CourseVedioQuestion</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-07 13:06
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseVedioQuestionReq implements Serializable {

    private static final long serialVersionUID = 5059447966551177594L;



    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode",  position = 2)
    @NotBlank(message = "用户编码不能为空")
    private String userUniqueCode;

    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频提问id", name = "videoId",  position = 3)
    @NotNull(message = "视频id不能为空")
    private Integer videoId;

    /**
     * 视频播放时间点(秒)
     */
    @ApiModelProperty(value = "视频播放时间点", name = "point",  position = 4)
    @NotNull(message = "视频播放时间点不能为空")
    private Integer point;

    /**
     * 问题标题
     */
    @ApiModelProperty(value = "问题标题", name = "title",  position = 5)
    @NotBlank(message = "问题标题不能为空")
    private String title;

    /**
     * 问题内容
     */
    @ApiModelProperty(value = "问题内容", name = "content",  position = 6)
    @NotBlank(message = "问题内容不能为空")
    private String content;


}
