package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.common.page.PageBean;
import com.gupao.edu.course.client.entity.CourseVideoQuestion;
import com.gupao.edu.course.client.req.center.CourseVedioQuestionReq;
import com.gupao.edu.course.client.req.good.CourseQuestionReq;
import com.gupao.edu.course.client.resp.center.AskAnsweResp;

/**
 * <p>
 * 问题记录表 服务类
 * </p>
 *
 * @author hduong
 * @since 2020-03-22
 */
public interface CourseAskQuestionService extends IService<CourseVideoQuestion> {

    /**
     * 根据课程id查询所有的评价(分页)
     * @param courseQuestionReq
     * @return
     */
    PageBean<AskAnsweResp> listPageCourseQuestionByCourseId(CourseQuestionReq courseQuestionReq);

}
