package com.gupao.edu.course.client.req.manager;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/3/25 20:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("批改作业-批量回复")
public class BatchReplyReq {
    /**
     * 是否批量
     */
    private boolean is_batch;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 作业标题ID
     */
    @ApiModelProperty(value = "作业标题ID", name = "title_id")
    private String title_id;
    /**
     * 作业内容
     */
    @ApiModelProperty(value = "作业内容", name = "homeworkContext")
    private String homeworkContext;

    /**
     * 作业标题
     */
    @ApiModelProperty(value = "作业标题", name = "homeworkName")
    private String homeworkName;
    /**
     * 得分等级
     */
    @ApiModelProperty(value = "得分等级", name = "gradeType")
    private Boolean gradeType;

    /**
     * 是否优秀作业
     */
    @ApiModelProperty(value = "是否优秀作业", name = "isGood")
    private Boolean isGood;


    /**
     * 批阅内容。
     */
    @ApiModelProperty(value = "批阅内容", name = "replyContext")
    private String replyContext;

    /**
     * 批阅老师ID
     */
    @ApiModelProperty(value = "批阅老师ID", name = "replyTeacherid")
    private Integer replyTeacherid;
}
