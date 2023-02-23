package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 〈作业评分实体〉
 *
 * @author wuzhenping
 * @create 2020/5/5
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScoreHomeworkReq {

    @ApiModelProperty(name = "courseHomeworkReplyId", value = "学员作业ID")
    private Integer courseHomeworkReplyId;

    @ApiModelProperty(name = "lecturerId", value = "关联讲师ID")
    private Integer lecturerId;

    @ApiModelProperty(name = "score", value = "作业得分")
    private Integer score;

    @ApiModelProperty(name = "isGood", value = "是否优秀作业")
    private Boolean isGood;

    @ApiModelProperty(name = "correct", value = "批阅内容")
    private String correct;
}
