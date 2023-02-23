package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.CourseHomeworkReplyPraise;

/**
 * <p>
 * 学员作业评论点赞表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseHomeworkReplyPraiseService extends IService<CourseHomeworkReplyPraise> {
    /**
     * 获取用户作业回复点赞信息
     * @param userUniqueCode 用户用户唯一编码
     * @param replyId 作业回复ID
     * @return 作业回复点赞信息
     */
    CourseHomeworkReplyPraise selectPraiseByUserAndReply(String userUniqueCode,Integer replyId);
}
