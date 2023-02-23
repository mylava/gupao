package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.entity.Lecturer;
import com.gupao.edu.course.server.mapper.LecturerMapper;
import com.gupao.edu.course.server.service.LecturerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师详情表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class LecturerServiceImpl extends ServiceImpl<LecturerMapper, Lecturer> implements LecturerService {

}
