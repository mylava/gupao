package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/21 10:58
 */
@Data
@ApiModel("作业回复实体类")
public class HomeworkReplyResp {

    @ApiModelProperty(name = "userUniqueCode", value = "答作业学员的用户唯一编码")
    private String userUniqueCode;

    @ApiModelProperty(name = "avatar", value = "用户头像")
    private String avatar;

    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(name = "createTime", value = "发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "homeworkReplyContent", value = "回复内容")
    private String homeworkReplyContent;

    @ApiModelProperty(name = "score", value = "作业得分")
    private Integer score;

    @ApiModelProperty(name = "isGood", value = "是否优秀作业")
    private Boolean isGood;

    @ApiModelProperty(name = "homeworkCommentRespList", value = "作业评论实体")
    private List<HomeworkCommentResp> homeworkCommentRespList;
}
