package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReply;
import com.gupao.edu.course.client.req.center.CourseVedioReplyReq;
import com.gupao.edu.course.server.mapper.CourseVedioReplyReqMapper;
import com.gupao.edu.course.server.service.CourseVedioReplyReqService;
import org.springframework.stereotype.Service;

/**
 * <h3>gupao-parent</h3>
 * <p>CourseVedioReplyReqService</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-07 13:24
 **/
@Service
public class CourseVedioReplyReqServiceImpl extends ServiceImpl<CourseVedioReplyReqMapper, CourseVideoQuestionReply>
    implements CourseVedioReplyReqService {
}
