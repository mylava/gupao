package com.gupao.edu.course.client.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <h3>app-backend</h3>
 * <p>讲师详情 返回实体</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-11 20:53
 **/
@Data
public class LecturerDTO implements Serializable {

    private static final long serialVersionUID = 2708192771363952136L;

    /**
     * 1: 讲师 id
     * 2: 讲师名字
     * 3: 讲师介绍
     * 4: 讲师图文
     * to add
     */
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 教师名字
     */
    private String name;

    /**
     * 教师简介
     */
    private String summary;

    /**
     * 教师图文详情
     */
    private String description;
}
