package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.req.center.CourseVedioReplyCommentReq;
import com.gupao.edu.course.server.mapper.CourseVedioReplyCommentMapper;
import com.gupao.edu.course.server.service.CourseVedioReplyCommentReqService;
import org.springframework.stereotype.Service;

/**
 * <h3>gupao-parent</h3>
 * <p>CourseVedioReplyCommentImpl</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-07 13:19
 **/
@Service
public class CourseVedioReplyCommentReqImpl extends ServiceImpl<CourseVedioReplyCommentMapper, CourseVedioReplyCommentReq>
    implements CourseVedioReplyCommentReqService{

}
