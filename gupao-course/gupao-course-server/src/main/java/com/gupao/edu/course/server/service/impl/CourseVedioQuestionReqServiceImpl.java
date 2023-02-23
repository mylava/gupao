package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.req.center.CourseVedioQuestionReq;
import com.gupao.edu.course.server.mapper.CourseVedioQuestionReqMapper;
import com.gupao.edu.course.server.service.CourseVedioQuestionReqService;
import org.springframework.stereotype.Service;

/**
 * <h3>gupao-parent</h3>
 * <p>CourseVedioQuestionReqServiceimpl</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-07 13:17
 **/
@Service
public class CourseVedioQuestionReqServiceImpl
    extends ServiceImpl<CourseVedioQuestionReqMapper, CourseVedioQuestionReq>
        implements CourseVedioQuestionReqService {
}
