package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.entity.CourseShare;
import com.gupao.edu.course.client.req.good.ShareCourseQueryReq;
import com.gupao.edu.course.server.mapper.CourseShareMapper;
import com.gupao.edu.course.server.service.CourseShareService;
import org.springframework.stereotype.Service;

/**
 * <h3>app-backend</h3>
 * <p>CourseShareServiceImpl </p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-03 15:25
 **/
@Service
public class CourseShareServiceImpl
        extends ServiceImpl<CourseShareMapper, CourseShare>
        implements CourseShareService {
//    继承了 BaseMapper 所以单表都有crud 功能 所以如果是单表后面不注入 CourseShareMapper 对象了

    @Override
    public int saveShare(ShareCourseQueryReq shareCourseQueryReq) {
        CourseShare courseShare = new CourseShare();
        courseShare.setUserUniqueCode(shareCourseQueryReq.getUserUniqueCode());
        courseShare.setCourseId(shareCourseQueryReq.getCourseId());
        return this.baseMapper.insert(courseShare);
    }
}
