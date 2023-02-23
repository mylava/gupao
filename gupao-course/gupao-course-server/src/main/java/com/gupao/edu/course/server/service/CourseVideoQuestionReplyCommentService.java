package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReplyComment;
import com.gupao.edu.course.client.req.center.CourseVedioReplyCommentReq;
import com.gupao.edu.course.client.resp.center.CourseVedioReplyComment;
import java.util.List;

/**
 * <p>
 * 视频答疑回复评论表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseVideoQuestionReplyCommentService extends IService<CourseVideoQuestionReplyComment> {

    /**
     * 新增评论
     * @param req
     */
    void addComment(CourseVedioReplyCommentReq req);

    /**
     * 根据回复id查询评论信息
     * @param replyId
     * @return
     */
    List<CourseVedioReplyComment> findCommentRespByReplyId(Integer replyId);
    /**
     * 根据回复id查询评论实体类信息
     * @param replyId
     * @return
     */
    List<CourseVideoQuestionReplyComment> findCommentsByReplyId(Integer replyId);
    /**
     * 根据parentId查询评论信息
     * @param parentId
     * @return
     */
    List<CourseVedioReplyComment> findCommentRespByParentId(Integer parentId);
    /**
     * 根据parentId查询评论实体类信息
     * @param parentId
     * @return
     */
    List<CourseVideoQuestionReplyComment> findCommentsByParentId(Integer parentId);
}
