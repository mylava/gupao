package com.gupao.edu.course.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <h3>app-backend</h3>
 * <p>课程 分享记录 实体</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-03 15:20
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "user_course_share")
public class CourseShare  implements Serializable {

    private static final long serialVersionUID = 7601257539611235465L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userUniqueCode;
    private Integer courseId;
    private Integer categoryId;
    private String channel;
    private String message;
    private LocalDate createTime;
    private LocalDate updateTime;

}
