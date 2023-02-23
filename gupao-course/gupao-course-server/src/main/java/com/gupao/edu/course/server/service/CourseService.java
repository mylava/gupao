package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.Course;
import com.gupao.edu.course.client.req.good.CourseListReq;
import com.gupao.edu.course.client.resp.CourseDetailResp;
import com.gupao.edu.course.client.resp.good.CourseListResp;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseService extends IService<Course> {

    int countPage(CourseListReq courseSubjectReq);

    IPage<CourseListResp> listCoursePage(CourseListReq courseSubjectReq);


    CourseDetailResp getCourseDetail(Integer courseId, String userUniqueCod);

    boolean saveOrUpdate(Integer id, Integer courseId);
}
