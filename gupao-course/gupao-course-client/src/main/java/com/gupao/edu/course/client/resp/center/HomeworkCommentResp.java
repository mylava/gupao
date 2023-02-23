package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/21 11:01
 * 作业回复评论实体
 */
@Data
@ApiModel("作业评论实体类")
public class HomeworkCommentResp {
    @ApiModelProperty(name = "id", value = "主键id")
    private Integer id;

    @ApiModelProperty(name = "parentId", value = "自关联id")
    private Integer parentId;

    @ApiModelProperty(name = "courseHomeworkReplyId", value = "学员作业id")
    private Integer courseHomeworkReplyId;

    @ApiModelProperty(name = "userUniqueCode", value = "用户唯一编码")
    private String userUniqueCode;

    @ApiModelProperty(name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(name = "commentContent", value = "评论内容")
    private String commentContent;
    /**
     * 根据 parentId = id 去设置所有的子评论
     */
    @ApiModelProperty(value = "评论的评论", name = "commentChildList")
    private List<HomeworkCommentResp> commentChildList;

}
