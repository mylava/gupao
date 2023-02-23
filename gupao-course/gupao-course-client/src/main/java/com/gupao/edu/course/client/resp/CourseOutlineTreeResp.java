package com.gupao.edu.course.client.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <h3>app-backend</h3>
 * <p>CourseOutlineTreeResp 课程大纲 多分支 无根节点 树</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-04 14:39
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("课程大纲树返回对象")
public class CourseOutlineTreeResp implements Serializable {

    /**
     * 大纲id
     */
    @ApiModelProperty(value = "大纲id", name = "id",  position = 1)
    private Integer id;

    /**
     * 是否目录节点 大纲 或者 视频
     */
    @ApiModelProperty(value = "是否是大纲目录", name = "outlineLevel",  position = 2)
    private Integer outlineLevel;

    /**
     * 大纲标题
     */
    @ApiModelProperty(value = "大纲名字", name = "outlineName",  position = 3)
    private String outlineName;

    /**
     * 视频播放时间
     */
    @ApiModelProperty(value = "播放时长", name = "playSeconds",  position = 4)
    private Long playSeconds;

    /**
     * 总时长
     */
    @ApiModelProperty(value = "总时长", name = "videoSeconds",  position = 5)
    private Long videoSeconds;

    /**
     * 视频学习 进度
     */
    @ApiModelProperty(value = "学习进度", name = "rateOfLearning",  position = 6)
    private String rateOfLearning ;

    /**
     *  02 或者 2-1 排序号 : 数据库 存放了这个字段
     */
    @ApiModelProperty(value = "排序号", name = "sort",  position = 7)
    private Integer sort;

    @ApiModelProperty(value = "排序串", name = "sortStr",  position = 7)
    private String sortStr;

    /**
     * 关联视频id
     */
    @ApiModelProperty(value = "关联视频id", name = "videoOrStreamId",  position = 8)
    private Integer videoOrStreamId;

    /**
     * 子大纲 ： 也就是视频集合
     */
    @ApiModelProperty(value = "节视频", name = "courseOutlineTreeResps",  position = 9)
    List<CourseOutlineTreeResp> childOutline;
}
