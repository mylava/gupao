package com.gupao.edu.course.client.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <h3>app-backend</h3>
 * <p>课程 返回信息</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-11 21:05
 **/
@Data
public class CourseDTO implements Serializable {


    private static final long serialVersionUID = -1670555532827078002L;


    private Integer id;

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
}
