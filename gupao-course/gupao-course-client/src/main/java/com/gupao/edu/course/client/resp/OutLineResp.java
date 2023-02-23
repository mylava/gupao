package com.gupao.edu.course.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/3/23 19:38
 * 课程大纲展示返回实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("课程大纲返回实体对象")
public class OutLineResp extends BaseResp{

    /**
     * 章节id
     */
    @ApiModelProperty(value = "章节id", name = "chapterId",  position = 3)
    private Integer chapterId;
    /**
     * 章节名字
     */
    @ApiModelProperty(value = "章节名字", name = "chapterName",  position = 4)
    private String chapterName;

    /**
     * 章节序号
     */
    @ApiModelProperty(value = "章节序号", name = "sort",  position = 5)
    private Integer sort;

    /**
     * 章节下所有小节的目录
     */
    @ApiModelProperty(value = "章节下所有小节的目录", name = "littleVideos",  position = 6)
    private List<LittleVideo> littleVideos;


}
