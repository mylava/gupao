package com.gupao.edu.course.client.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author hduong
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("teacher_team")
@ApiModel("讲师对象")
public class TeacherTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "teacher_id", type = IdType.AUTO)
    @ApiModelProperty(value = "讲师Id", name = "teacherId", position = 1)
    private Integer teacherId;

    /**
     * 讲师名字
     */
    @ApiModelProperty(value = "讲师名称", name = "teacherName", position = 2)
    private String teacherName;

    /**
     * 是否明星讲师 1是0否
     */
    @ApiModelProperty(value = "是否明显讲师", name = "teacherStar", position = 3)
    private Integer teacherStar;

    /**
     * 讲师简介
     */
    @ApiModelProperty(value = "讲师简介", name = "teacherResume", position = 4)
    private String teacherResume;

    /**
     * 讲师职位
     */
    @ApiModelProperty(value = "讲师职位", name = "teacherPosition", position = 5)
    private String teacherPosition;

    /**
     * 讲师标签
     */
    @ApiModelProperty(value = "讲师标签", name = "teacherTag", position = 6)
    private String teacherTag;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式", name = "teacherContact", position = 7)
    private String teacherContact;

    /**
     * 图片地址
     */
    private String teacherImage;

    /**
     * 讲师排序
     */
    private Integer teacherSort;

    /**
     * 讲师状态
     */
    private Integer teacherStatus;

    /**
     * 是否删除 默认0 否
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 更新时间
     */
    private LocalDateTime utime;

    /**
     * 创建人ID
     */
    private Integer creatorId;

    /**
     * 创建人名称
     */
    private String creator;


}
