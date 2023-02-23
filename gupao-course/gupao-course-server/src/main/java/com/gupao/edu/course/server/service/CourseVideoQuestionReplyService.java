package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReply;
import com.gupao.edu.course.client.req.center.CourseVedioReplyReq;
import com.gupao.edu.course.client.req.center.QuestionReplyQueryReq;
import com.gupao.edu.course.client.resp.center.CourseVedioReplyResp;
import java.util.List;

/**
 * <p>
 * 视频答疑回复表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseVideoQuestionReplyService extends IService<CourseVideoQuestionReply> {

    /**
     * 新增回复
     * @param courseVedioReplyReq
     */
    void addReply(CourseVedioReplyReq courseVedioReplyReq);
    /**
     * 根据问题id查询回复实体信息
     * @param questionId
     */
    List<CourseVideoQuestionReply> findReplyListByQuestionId(Integer questionId);
    /**
     * 根据问题id查询回复信息
     * @param questionId
     */
    IPage<CourseVedioReplyResp> findReplyRespListByQuestionId(Integer questionId,
        QuestionReplyQueryReq req);
}
