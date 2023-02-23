package com.gupao.edu.course.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.UserPrefix;
import com.gupao.edu.course.client.entity.UserCourseProgress;
import com.gupao.edu.course.client.resp.UserCourseProgressVO;
import com.gupao.edu.course.server.enums.CacheKeyEnum;
import com.gupao.edu.course.server.mapper.UserCourseProgressMapper;
import com.gupao.edu.course.server.service.UserCourseProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 我的课程播放进度表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserCourseProgressServiceImpl extends ServiceImpl<UserCourseProgressMapper, UserCourseProgress> implements UserCourseProgressService {

    @Autowired
    private UserCourseProgressMapper userCourseProgressMapper;

    /**
     * 学习时长 更新 频繁 是否使用 缓存 todo
     *
     * @param userUniqueCode
     * @param isTaday
     * @return
     */
    @Override
    public Long computerTotalStudyTime(String userUniqueCode, boolean isTaday) {
//        String totalCount = CacheUtil.get(UserPrefix.USER_COURSE, CacheKeyEnum.STUDY_TOTAL_TIME.getKey() + userUniqueCode);
//        if(totalCount == null) {
//            Long total = userCourseProgressMapper.computerTotalStudyTime(userUniqueCode);
//            CacheUtil.set(UserPrefix.USER_COURSE, CacheKeyEnum.STUDY_TOTAL_TIME.getKey() + userUniqueCode, String.valueOf(total));
//            return total;
//        } else {
//            //转化成分钟
//            return Long.parseLong(totalCount) / 60;
//        }
        Long total = userCourseProgressMapper.computerTotalStudyTime(userUniqueCode, isTaday);
        return total;
//        if(total != null) {
//            return total / 60;
//        } else {
//            return 0L;
//        }
    }

    @Override
    public UserCourseProgress getLastStudyVedioAndOutline(String userUniqueCode, Integer coureseId) {
        Wrapper<UserCourseProgress> wrapper = new QueryWrapper<>(new UserCourseProgress())
                .eq("user_unique_code", userUniqueCode)
                .eq("course_id", coureseId)
                .orderByDesc("update_time");
        List<UserCourseProgress> userCourseProgresses = userCourseProgressMapper.selectList(wrapper);
        if(userCourseProgresses != null && userCourseProgresses.size() > 0) {
            return userCourseProgresses.get(0);
        }
        //没有最近上课 返回null
        return null;
    }

    @Override
    public UserCourseProgressVO getLastStudyVedioVO(String userUniqueCode, Integer coureseId) {
        List<UserCourseProgressVO> userCourseProgressVOS = userCourseProgressMapper.getLastStudyVedioVO(userUniqueCode, coureseId);
        if(userCourseProgressVOS != null && userCourseProgressVOS.size() > 0) {
            return userCourseProgressVOS.get(0);
        }
        return null;
    }
}
