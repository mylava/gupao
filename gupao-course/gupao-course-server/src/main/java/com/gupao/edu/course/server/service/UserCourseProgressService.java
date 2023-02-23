package com.gupao.edu.course.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.course.client.entity.UserCourseProgress;
import com.gupao.edu.course.client.resp.UserCourseProgressVO;

/**
 * <p>
 * 我的课程播放进度表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserCourseProgressService extends IService<UserCourseProgress> {

    /**
     * 计算用户学习总时长
     * @param userUniqueCode
     * @param isTaday  是否今天
     * @return
     */
    Long computerTotalStudyTime(String userUniqueCode, boolean isTaday);

    /**
     * 获取 最近 上的课程 大纲 视频
     * @param userUniqueCode
     * @param coureseId
     * @return
     */
    UserCourseProgress getLastStudyVedioAndOutline(String userUniqueCode, Integer coureseId);

    /**
     * 获取 最近 上的课程 大纲 视频 （包含大纲标题 大纲名字）
     * @param userUniqueCode
     * @param coureseId
     * @return
     */
    UserCourseProgressVO getLastStudyVedioVO(String userUniqueCode, Integer coureseId);
}
