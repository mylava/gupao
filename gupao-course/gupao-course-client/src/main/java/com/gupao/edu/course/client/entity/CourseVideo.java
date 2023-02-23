package com.gupao.edu.course.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 视频资源表
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属学科id:(JAVA、大数据,对应字典表)
     */
    private Integer gradeId;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频时长单位秒
     */
    private Integer duration;

    /**
     * 播放次数
     */
    private Integer viewNum;

    /**
     * 文件大小单位M
     */
    private Integer fileSize;

    /**
     * 状态(-1:审核不通过，0:审核中，1:审核通过)
     */
    private Integer auditState;

    /**
     * 预留字段：保利威云视频状态
     */
    private String cloudVideoState;

    /**
     * 本视频的图片展示地址
     */
    private String imageUrl;

    /**
     * 保利威视频ID
     */
    private String polyvVideoId;

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
