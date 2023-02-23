package com.gupao.edu.course.client.req.good;/**
 * Created by dudu on 2020/3/27.
 */

import com.gupao.edu.course.client.req.BasePageParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>gupao-parent</h3>
 * <p>课程疑问 请求实体</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-03-27 23:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("根据课程查询相关疑问")
public class CourseQuestionReq extends BasePageParams {

    @ApiModelProperty(value = "课程Id", name = "courseId")
    private Integer courseId;

    @ApiModelProperty(value = "视频Id", name = "vedioId")
    private Integer vedioId;

    @ApiModelProperty(value = "用户Id", name = "userId")
    private Integer userId;

    @ApiModelProperty(value = "问题Id", name = "questionId")
    private Integer questionId;
}
