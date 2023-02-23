package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.course.client.entity.UserCourse;
import com.gupao.edu.course.client.req.center.StudyCenterReq;
import com.gupao.edu.course.client.resp.center.MyCourseResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 我购买的课程表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserCourseMapper extends BaseMapper<UserCourse> {

    /**
     * 查询 我购买的课程
     * @return
     */
    List<MyCourseResp> listMyCourse(@Param("condition") StudyCenterReq studyCenterReq);
}
