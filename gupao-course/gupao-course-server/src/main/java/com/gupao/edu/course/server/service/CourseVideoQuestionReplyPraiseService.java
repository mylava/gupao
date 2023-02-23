package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReplyPraise;
import com.gupao.edu.course.client.req.center.QuestionReplyPraiseReq;

/**
 * <p>
 * 答疑点赞表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseVideoQuestionReplyPraiseService extends IService<CourseVideoQuestionReplyPraise> {

    void questionReplyPraise(QuestionReplyPraiseReq req);

}
