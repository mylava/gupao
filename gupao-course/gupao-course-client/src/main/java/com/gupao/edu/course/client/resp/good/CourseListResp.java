package com.gupao.edu.course.client.resp.good;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/16 10:59
 * 返回课程列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("课程列表")
public class CourseListResp {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", name = "id",  position = 1)
    private Integer id;

    /**
     * 课程分类id（训练营、精品小课、vip课，对应字典表）
     */
    @ApiModelProperty(value = "课程分类id（训练营、精品小课、vip课，对应字典表）", name = "categoryId",  position = 2)
    private Integer categoryId;

    /**
     * 所属学科id:(JAVA、大数据,对应字典表)
     */
    @ApiModelProperty(value = "所属学科id:(JAVA、大数据,对应字典表)", name = "gradeId",  position = 1)
    private Integer gradeId;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", name = "courseName",  position = 3)
    private String courseName;


    /**
     * 课程封面图片
     */
    @ApiModelProperty(value = "课程封面图片", name = "imageUrl",  position = 4)
    private String imageUrl;

    /**
     * 观看人数
     */
    @ApiModelProperty(value = "观看人数", name = "viewNum",  position = 5)
    private Integer viewNum;


    /**
     * 评论人数
     */
    @ApiModelProperty(value = "评论人数", name = "commentNum",  position = 6)
    private Integer commentNum;

    /**
     * 关注人数 : 课程离开播时间小于1个月的时候进行计算 并展示
     */
    @ApiModelProperty(value = "关注人数", name = "attentionNum",  position = 7)
    private Integer attentionNum;



    /**
     * 现价，有效价格
     */
    @ApiModelProperty(value = "现价，有效价格", name = "cost",  position = 8)
    private Integer cost;

    /**
     * 原价，无效价格
     */
    @ApiModelProperty(value = "原价，无效价格", name = "price",  position = 9)
    private Integer price;

    /**
     * 老师的Id，多个老师用json {"1":"10001","2"...}
     * 主讲老师
     */
    @ApiModelProperty(value = "老师的Id，多个老师用json {\"1\":\"10001\",\"2\"...}", name = "lecturerIds",  position = 10)
    private String lecturerIds;

    /**
     * 图文详情，富文本应编码存储
     */
    @ApiModelProperty(value = "图文详情，富文本应编码存储", name = "description",  position = 11)
    private String description;


    /**
     * 课程的开播时间
     */
    @ApiModelProperty(value = "课程的开播时间", name = "startTime",  position = 12)
    private LocalDateTime startTime;

    /**
     * 课程的结束时间
     */
    @ApiModelProperty(value = "课程的结束时间", name = "endTime",  position = 13)
    private LocalDateTime endTime;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime",  position = 14)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime",  position = 15)
    private LocalDateTime updateTime;

    /**
     * 通过 计算获得
     * 前端y也可以 通过开播时间进行计算
     * */
    @ApiModelProperty(value = "距离开播剩余天数", name = "remainingDays",  position = 16)
    private Integer remainingDays;

    /**
     * 如果是公开课还有一个排期数 : 待开放 TODO
     */
    private Integer number;
}
