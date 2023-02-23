package com.gupao.edu.course.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程分类id（训练营、精品小课、vip课，对应字典表）
     */
    private Integer categoryId;

    /**
     * 所属学科id:(JAVA、大数据,对应字典表)
     */
    private Integer gradeId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程主题名称
     */
    private String courseTitle;

    /**
     * 课程封面图片
     */
    private String imageUrl;

    /**
     * 观看人数
     */
    private Integer viewNum;

    /**
     * 已学人数
     */
    private Integer studyNum;

    /**
     * 评论人数
     */
    private Integer commentNum;

    /**
     * 关注人数
     */
    private Integer attentionNum;

    /**
     * 好评数
     */
    private Integer praiseNum;

    /**
     * 课程视频总时长(秒)
     */
    private Integer totalMinutes;

    /**
     * 好评度
     */
    private Float praiseScore;

    /**
     * 平均评分
     */
    private Float averageScore;

    /**
     * 答疑数， 维护在课程表中,方便查询
     */
    private Integer replyCount;

    /**
     * 现价，有效价格
     */
    private Integer cost;

    /**
     * 原价，无效价格
     */
    private Integer price;

    /**
     * 老师的Id，多个老师{"1":"10001","2"...}
     */
    private String lecturerIds;

    /**
     * 图文详情，富文本应编码存储
     */
    private String description;

    /**
     * 0未删除，1已删除
     */
    private Boolean isDeleted;

    /**
     * 课程的开播时间
     */
    @TableField("start_Time")
    private LocalDateTime startTime;

    /**
     * 课程的结束时间
     */
    @TableField("endTime")
    private LocalDateTime endTime;

    /**
     * 上架时间
     */
    private LocalDateTime putawayTime;

    /**
     * 创建者的userId
     */
    private Integer createUserId;

    /**
     * 修改者的userId
     */
    private Integer updateUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
