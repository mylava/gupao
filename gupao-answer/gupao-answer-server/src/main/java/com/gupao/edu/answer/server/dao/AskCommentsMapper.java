package com.gupao.edu.answer.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.answer.server.entity.AskComments;
import com.gupao.edu.answer.server.entity.req.CommentsReq;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author cxp
 * @since 2020-03-22
 */
public interface AskCommentsMapper extends BaseMapper<AskComments> {

    IPage<AskComments> queryListBySourceId(IPage<AskComments> page, CommentsReq commentsReq);

    List<AskComments> queryAll(AskComments replyCommentsForm);
}
