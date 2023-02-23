package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.UserCourse;
import com.gupao.edu.course.client.req.center.StudyCenterReq;
import com.gupao.edu.course.client.resp.center.MyCourseResp;

import java.util.List;

/**
 * <p>
 * 我购买的课程表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserCourseService extends IService<UserCourse> {

    /**
     * 查询 我购买的课程 列表信息
     * @return
     */
    List<MyCourseResp> listMyCourse(StudyCenterReq studyCenterReq);
}
