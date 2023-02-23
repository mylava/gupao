package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.UserCourseFavorite;
import com.gupao.edu.course.client.req.CourseFavoriteReq;
import com.gupao.edu.course.client.resp.good.CourseListResp;

import java.util.List;

/**
 * <p>
 * 课程收藏表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserCourseFavoriteService extends IService<UserCourseFavorite> {

    /**
     * 保存 课程 收藏
     * @param courseId
     * @param userUniqueCode
     * @return
     */
    int saveCourseFavorite(Integer courseId, String userUniqueCode);

    UserCourseFavorite selectByCourseIdAndUserUniqueCode(Integer courseId, String userUniqueCode);

    List<CourseListResp> listCourseFavorite(CourseFavoriteReq courseFavoriteReq);
}
