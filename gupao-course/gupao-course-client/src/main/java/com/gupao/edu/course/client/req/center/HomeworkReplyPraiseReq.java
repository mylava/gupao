package com.gupao.edu.course.client.req.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author wuzhenping
 * @Date 2020/5/5 17:36
 */
@Data
@ApiModel("学员作业评论点赞实体")
public class HomeworkReplyPraiseReq {

    /**
     * 学员作业id
     */
    @ApiModelProperty(name = "学员作业id",value = "courseHomeworkReplyId")
    private Integer courseHomeworkReplyId;

    /**
     * 用户唯一编码
     */
    @ApiModelProperty(name = "用户唯一编码",value = "userUniqueCode")
    private String userUniqueCode;


    /**
     * 赞或踩,0赞,1踩
     */
    @ApiModelProperty(name = "赞或踩,0赞,1踩",value = "type")
    private Integer type;





}
