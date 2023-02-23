package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseVideoQuestion;
import com.gupao.edu.course.client.req.center.CourseVedioQuestionReq;
import com.gupao.edu.course.client.req.center.QuestionReplyQueryReq;
import com.gupao.edu.course.client.req.center.UserAskReq;
import com.gupao.edu.course.client.resp.center.AskAnsweResp;
import com.gupao.edu.course.client.resp.center.CourseVedioQuestionResp;

/**
 * <p>
 * 视频答疑问题表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseVideoQuestionService extends IService<CourseVideoQuestion> {

    /**
     * 新增提问
     * @param req
     */
    void addCourseAsk(CourseVedioQuestionReq req);

    /**
     * 根据问题id查询提问详情
     * @param req
     * @return
     */
    AskAnsweResp findQuestionInfoById(QuestionReplyQueryReq req);

    /**
     * 分页查询提问
     * @param userAskReqe
     * @return
     */
    IPage<CourseVedioQuestionResp> findQuestionPage(UserAskReq userAskReqe);
}
