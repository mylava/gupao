package com.gupao.edu.course.client.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gupao.edu.course.client.resp.good.TeacherResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/5/4 20:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("课程详情实体类")
public class CourseDetailResp {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "课程id", name = "id",  position = 1)
    private Integer id;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", name = "courseName",  position = 2)
    private String courseName;
    /**
     * 抢购结束时间
     */
    @ApiModelProperty(value = "抢购结束时间", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date buyEndDateTime;
    /**
     * 剩余名额数
     */
    @ApiModelProperty(value = "剩余名额数", position = 4)
    private Integer remainingQuota;
    /**
     * 课程主题名称
     */
    @ApiModelProperty(value = "课程主题名称", name = "courseTitle",  position = 5)
    private String courseTitle;

    /**
     * 课程封面图片
     */
    @ApiModelProperty(value = "课程封面图片", name = "imageUrl",  position = 6)
    private String imageUrl;


    /**
     * 已学人数
     */
    @ApiModelProperty(value = "已学人数", name = "studyNum",  position = 7)
    private Integer studyNum;

    /**
     * 评论人数
     */
    @ApiModelProperty(value = "评论人数", name = "commentNum",  position = 8)
    private Integer commentNum;


    /**
     * 课程视频总时长(分)
     */
    @ApiModelProperty(value = "课程视频总时长(分)", name = "totalMinutes",  position = 9)
    private Integer totalMinutes;

    /**
     * 好评度
     */
    @ApiModelProperty(value = "好评度", name = "praiseScore",  position = 10)
    private Float praiseScore;



    /**
     * 现价，有效价格
     */
    @ApiModelProperty(value = "现价，有效价格", name = "cost",  position = 11)
    private Integer cost;

    /**
     * 原价，无效价格
     */
    @ApiModelProperty(value = "原价，无效价格", name = "price",  position = 12)
    private Integer price;

    /**
     * 图文详情，富文本应编码存储
     */
    @ApiModelProperty(value = "图文详情，富文本应编码存储", name = "description",  position = 13)
    private String description;



    //******************新增老师集合具体信息
    @ApiModelProperty(value = "讲师集合", name = "teacherTeams",  position = 14)
    private List<TeacherResp> teacherTeams;
}
