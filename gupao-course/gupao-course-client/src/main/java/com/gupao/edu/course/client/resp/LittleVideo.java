package com.gupao.edu.course.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/3/23 19:51
 * 小节展示
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("小节实体类包含视频播放地址")
public class LittleVideo {
    /**
     * 小节id
     */
    @ApiModelProperty(value = "小节id", name = "littleId",  position = 1)
    private Integer littleId;
    /**
     * 小节标题
     */
    @ApiModelProperty(value = "小节标题", name = "littleName",  position = 2)
    private String littleName;
    /**
     * 章节序号
     */
    @ApiModelProperty(value = "章节序号", name = "sort",  position = 3)
    private Integer sort;
    /**
     * 小节视频长度（分）
     */
    @ApiModelProperty(value = "视频长度", name = "videoSize",  position = 4)
    private Integer videoSize;

    @ApiModelProperty(value = "已播放时长", name = "playSeconds",  position = 4)
    private Integer playSeconds;
    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父章id", name = "littleVideos",  position = 5)
    private Integer parentId;
    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频id", name = "videoId",  position = 6)
    private String videoId;
    /**
     * 小节已学进度
     */
    @ApiModelProperty(value = "小节已学进度", name = "studyProcessed",  position = 7)
    private String studyProcessed;

    //**************TODO 新增是否可以试听字段 以及可以试听时间

    @ApiModelProperty(value = "是否可以试听", name = "onTry",  position = 8)
    private String onTry;

    @ApiModelProperty(value = "试听活动开始时间", name = "tryTimeStart",  position = 9)
    private LocalDate tryTimeStart;

    @ApiModelProperty(value = "试听活动结束时间", name = "tryTimeEnd",  position = 10)
    private LocalDate tryTimeEnd;

    @ApiModelProperty(value = "试听时长，单位秒 -1表示永久", name = "limitTime",  position = 11)
    private Integer limitTime;



}
