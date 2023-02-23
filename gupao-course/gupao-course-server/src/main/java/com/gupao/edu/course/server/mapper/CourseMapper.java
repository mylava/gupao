package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.course.client.entity.Course;
import com.gupao.edu.course.client.resp.good.CourseListResp;

import java.util.List;

/**
 * <p>
 * 课程表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 根据课程类型 获取课程列表
     * @param page
     * @param category_id
     * @return
     */
    List<CourseListResp> listCoursePage(Page<CourseListResp> page, Integer category_id,Integer grade_id);
}
