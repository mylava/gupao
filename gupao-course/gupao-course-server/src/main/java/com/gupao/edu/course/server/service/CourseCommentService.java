package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.common.page.PageBean;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.course.client.entity.CourseComment;
import com.gupao.edu.course.client.req.center.CourseCommentResp;
import com.gupao.edu.course.client.req.good.CourseCommonParamReq;
import com.gupao.edu.course.client.resp.good.CourseCommentCensusResp;

/**
 * <p>
 * 课程评论表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseCommentService extends IService<CourseComment> {

    PageBean<CourseCommentResp> findPageCourseCommentByCourseId(Integer courseId);

    CourseCommentCensusResp findCommentCensusByCourseId(Integer courseId);

    Result courseComment(CourseCommonParamReq courseCommonQueryReq);
}
