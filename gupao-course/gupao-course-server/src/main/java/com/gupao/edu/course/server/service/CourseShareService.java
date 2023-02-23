package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseShare;
import com.gupao.edu.course.client.req.good.ShareCourseQueryReq;

/**
 * <p>
 * 课程分享服务 接口 定义
 * </p>
 *
 * @author hduong
 * @since 2020-03-22
 */
public interface CourseShareService extends IService<CourseShare> {


    /**
     *
     * @param shareCourseQueryReq
     * @return
     */
    int saveShare(ShareCourseQueryReq shareCourseQueryReq);
}
