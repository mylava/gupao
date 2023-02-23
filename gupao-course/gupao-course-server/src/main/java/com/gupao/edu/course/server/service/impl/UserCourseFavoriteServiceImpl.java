package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.course.client.entity.UserCourseFavorite;
import com.gupao.edu.course.client.req.CourseFavoriteReq;
import com.gupao.edu.course.client.resp.good.CourseListResp;
import com.gupao.edu.course.server.mapper.UserCourseFavoriteMapper;
import com.gupao.edu.course.server.service.UserCourseFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程收藏表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserCourseFavoriteServiceImpl extends ServiceImpl<UserCourseFavoriteMapper, UserCourseFavorite> implements UserCourseFavoriteService {


    @Autowired
    UserCourseFavoriteMapper courseFavoriteMapper;

    @Override
    public UserCourseFavorite selectByCourseIdAndUserUniqueCode(Integer courseId, String userUniqueCode) {
        QueryWrapper<UserCourseFavorite> queryWrapper = new QueryWrapper<>(new UserCourseFavorite());
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("user_unique_code", userUniqueCode);
        UserCourseFavorite result = courseFavoriteMapper.selectOne(queryWrapper);
        return  result;
    }

    @Override
    public int saveCourseFavorite(Integer courseId, String userUniqueCode) {
        UserCourseFavorite userCourseFavorite = new UserCourseFavorite();
        userCourseFavorite.setCourseId(courseId);
        userCourseFavorite.setUserUniqueCode(userUniqueCode);
        return this.baseMapper.insert(userCourseFavorite);
    }

    /**
     * 查询用户收藏课程列表
     * @param courseFavoriteReq
     * @return
     */
    @Override
    public List<CourseListResp> listCourseFavorite(CourseFavoriteReq courseFavoriteReq) {
        //重新计算 limit 后面的值 : 开始 记录数 + 多少条
        int size = courseFavoriteReq.getPageSize();
        if(size <= 0) {
            courseFavoriteReq.setPageSize(10);
        } else {
            courseFavoriteReq.setPageSize(courseFavoriteReq.getPageNum() * size);
        }
        //设置 开始记录数
        if(courseFavoriteReq.getPageNum() <= 0) {
            courseFavoriteReq.setPageNum(1);
        } else {
            courseFavoriteReq.setPageNum((courseFavoriteReq.getPageNum() - 1) * courseFavoriteReq.getPageSize());
        }
        return  courseFavoriteMapper.listCourseFavorite(courseFavoriteReq);
    }

}
