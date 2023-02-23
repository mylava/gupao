package com.gupao.edu.course.client.resp.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/21 11:54
 */
@Data
@ApiModel("视频节点问答实体")
public class CourseQuestionResp {
    @ApiModelProperty(value = "提问id", name = "id",  position = 1)
    private Integer id;

    @ApiModelProperty(value = "提问的用户编码", name = "userUniqueCode",  position = 2)
    private String userUniqueCode;

    @ApiModelProperty(value = "视频播放时间点", name = "point",  position = 1)
    private Integer point;

    @ApiModelProperty(value = "提问用户昵称", name = "userName",  position = 3)
    private String userName;

    @ApiModelProperty(value = "视频id", name = "videoId",  position = 4)
    private Integer videoId;

    @ApiModelProperty(value = "提问标题", name = "title",  position = 6)
    private String title;

    @ApiModelProperty(value = "提问内容", name = "content",  position = 7)
    private String content;

    @ApiModelProperty(value = "回答数", name = "replyCount",  position = 8)
    private Integer replyCount;

    @ApiModelProperty(value = "浏览量", name = "browseCount",  position = 9)
    private Integer browseCount;

    @ApiModelProperty(value = "提问创建时间", name = "createTime",  position = 10)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "提问修改时间", name = "updateTime",  position = 11)
    private LocalDateTime updateTime;
}
