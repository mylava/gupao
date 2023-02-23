package com.gupao.edu.course.client.resp.center;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * 作业详情
 * @create 2020/3/25
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("作业详情返回实体")
public class HomeworkDetailResp {

    @ApiModelProperty(name = "homeworkTitle", value = "作业标题")
    private String homeworkTitle;
    @ApiModelProperty(name = "courseOutLineName", value = "大纲名字")
    private String courseOutLineName;
    @ApiModelProperty(name = "homeworkContext", value = "作业内容")
    private String homeworkContext;
    @ApiModelProperty(name = "createTime", value = "作业发布时间")
    private Date createTime;
    //TODO:暂无 浏览量
    @ApiModelProperty(name = "PV", value = "浏览量")
    private Integer PV;
    @ApiModelProperty(name = "isCommit",value = "当前用户是否提交")
    private boolean isCommit;
    @ApiModelProperty(name = "homeworkReplyRespIPage", value = "作业回复列表")
    private IPage<HomeworkReplyResp> homeworkReplyRespIPage;


}
