package com.gupao.edu.course.client.resp.center;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/3/27 17:56
 * 答疑实体类 : 时间点 用户 问题标题 回答集合 回答人数 浏览人数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("答疑返回实体类")
public class AskAnsweResp {
    /**
     * 视频播放时间点(秒)
     */
    @ApiModelProperty(value = "视频播放时间点", name = "point",  position = 1)
    private Integer point;
    /**
     * 提问人
     */
    @ApiModelProperty(value = "提问人编码", name = "userUniqueCode",  position = 2)
    private String userUniqueCode;

    /**
     * 提问人昵称
     */
    @ApiModelProperty(value = "提问人昵称", name = "userName",  position = 3)
    private String userName;

    /**
     * 回答数
     */
    @ApiModelProperty(value = "回答数", name = "replyCount",  position = 4)
    private Integer replyCount;
    /**
     * 提问时间
     */
    @ApiModelProperty(value = "提问时间", name = "createTime",  position = 5)
    private LocalDateTime createTime;
    /**
     * 浏览人数
     */
    @ApiModelProperty(value = "浏览人数", name = "browseCount",  position = 6)
    private Integer browseCount;
    /**
     * 问题内容
     */
    @ApiModelProperty(value = "问题内容", name = "content",  position = 7)
    private String content;
    /**
     * 问题回答集合
     */
    @ApiModelProperty(value = "问题回答集合", name = "list",  position = 8)
    private IPage<CourseVedioReplyResp> replyPage;
}
