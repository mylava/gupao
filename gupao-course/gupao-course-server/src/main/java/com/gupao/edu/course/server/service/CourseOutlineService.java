package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseOutline;
import com.gupao.edu.course.client.resp.CourseOutlineTreeResp;
import com.gupao.edu.course.client.resp.OutLineResp;

import java.util.List;

/**
 * <p>
 * 课程大纲表（关联视频资源） 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseOutlineService extends IService<CourseOutline> {

    List<OutLineResp> findOutLineByCourseId(Integer courseId);

    /**
     * 计算课程 视频 总时长
     * @param courseId
     * @return
     */
    Long computeCourseVedioTime(Integer courseId);

    List<CourseOutlineTreeResp> findOutLineCourseByCourseId(String userUniqueCode, Integer courseId);

}
