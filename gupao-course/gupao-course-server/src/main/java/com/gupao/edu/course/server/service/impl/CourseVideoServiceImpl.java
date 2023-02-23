package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.entity.CourseVideo;
import com.gupao.edu.course.server.mapper.CourseVideoMapper;
import com.gupao.edu.course.server.service.CourseVideoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频资源表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class CourseVideoServiceImpl extends ServiceImpl<CourseVideoMapper, CourseVideo> implements CourseVideoService {

}
