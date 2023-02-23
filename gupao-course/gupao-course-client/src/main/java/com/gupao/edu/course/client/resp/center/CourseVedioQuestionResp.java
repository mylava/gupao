package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <h3>gupao-parent</h3>
 * <p>course_vedio_question</p>
 * course_vedio_question :  答疑问题 对象
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-06 22:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("视频提问实体")
public class CourseVedioQuestionResp implements Serializable {

    private static final long serialVersionUID = -6263493380130499772L;
    @ApiModelProperty(value = "视频播放时间点", name = "point",  position = 1)
    private Integer point;
    @ApiModelProperty(value = "提问人数", name = "peopleNum",  position = 2)
    private Integer peopleNum;
    @ApiModelProperty(value = "问答实体", name = "respList",  position = 3)
    private List<CourseQuestionResp> respList;

}
