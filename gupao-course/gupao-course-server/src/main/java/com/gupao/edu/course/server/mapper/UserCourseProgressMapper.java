package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.course.client.entity.UserCourseProgress;
import com.gupao.edu.course.client.resp.UserCourseProgressVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 我的课程播放进度表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserCourseProgressMapper extends BaseMapper<UserCourseProgress> {

    /**
     * 计算用户 总共学习时长
     * @param userUniqueCode
     * @param isTaday
     * @return
     */
    Long computerTotalStudyTime(@Param("userUniqueCode") String userUniqueCode, @Param("isTaday") boolean isTaday);

    List<UserCourseProgressVO> getLastStudyVedioVO(@Param("userUniqueCode") String userUniqueCode,@Param("courseId") Integer coureseId);
}
