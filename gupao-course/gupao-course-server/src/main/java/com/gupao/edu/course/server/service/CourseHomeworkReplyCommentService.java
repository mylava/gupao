package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseHomeworkReplyComment;
import com.gupao.edu.course.client.resp.center.HomeworkCommentResp;

import java.util.List;

/**
 * <p>
 * 学员作业评论表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseHomeworkReplyCommentService extends IService<CourseHomeworkReplyComment> {

    /**
     * 根据学员答作业表ID统计其评论数
     * @param replyId 学员答作业表ID
     * @return 评论数
     */
    Integer countCommentNumByReplyId(Integer replyId);

    /**
     * 根据学员答作业获取其答作业评论列表
     * @param replyId 学员答作业表ID
     * @return 答作业评论列表
     */
    List<CourseHomeworkReplyComment> selectCommentByReplyId(Integer replyId);

    /**
     * 根据学员答作业获取其答作业评论的评论列表
     * @param parentId 学员答作业下评论评论的ID
     * @return 答作业评论的评论列表
     */
    List<CourseHomeworkReplyComment> selectCommentByParentId(Integer parentId);

    /**
     * 学员评论作业信息列表
     * @param replyId 答作业ID
     * @return 学员评论作业信息列表
     */
    List<HomeworkCommentResp> fingHomeworkCommentRespList(Integer replyId);

    /**
     * 获取评论评论的信息列表
     * @param parentId 评论评论的ID
     * @return 评论评论的信息列表
     */
    List<HomeworkCommentResp> findCommentChildList(Integer parentId);


}
