package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.course.client.entity.UserCourseFavorite;
import com.gupao.edu.course.client.req.CourseFavoriteReq;
import com.gupao.edu.course.client.resp.good.CourseListResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程收藏表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserCourseFavoriteMapper extends BaseMapper<UserCourseFavorite> {

    /**
     * 查询 用户 收藏课程列表
     * @param courseFavoriteReq
     * @return
     */
    List<CourseListResp> listCourseFavorite(@Param("condition") CourseFavoriteReq courseFavoriteReq);

}
