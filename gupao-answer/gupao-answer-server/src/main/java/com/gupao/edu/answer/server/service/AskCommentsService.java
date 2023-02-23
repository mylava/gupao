package com.gupao.edu.answer.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.answer.server.entity.AskComments;
import com.gupao.edu.answer.server.entity.req.CommentsQueryReplyReq;
import com.gupao.edu.answer.server.entity.req.CommentsReq;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author cxp
 * @since 2020-03-22
 */
public interface AskCommentsService extends IService<AskComments> {

    AskComments queryById(Integer id);

    IPage<AskComments> queryListBySourceId(CommentsReq commentsReq);

    void addComments(AskComments commentsForm);

    void addReplyComments(AskComments askComments);

    //查询回复信息
    IPage<AskComments> pageQueryByCommentsReply(CommentsQueryReplyReq commentsForm);
}
