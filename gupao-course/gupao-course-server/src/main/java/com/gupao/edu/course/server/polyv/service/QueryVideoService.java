package com.gupao.edu.course.server.polyv.service;


import com.gupao.edu.course.server.polyv.bean.request.queryVideos.QueryByAuthorizationStateRequest;
import com.gupao.edu.course.server.polyv.bean.request.queryVideos.QueryByTagRequest;
import com.gupao.edu.course.server.polyv.bean.request.queryVideos.QueryByTitleOrClassificationRequest;
import com.gupao.edu.course.server.polyv.bean.result.PLVodCommonResult;

/**
 * 查找视频 Service
 *
 * @author zhangpan@centaline.com
 * createTime 2020/5/6
 */
public interface QueryVideoService {

    /**
     * 根据授权播放开关状态查询视频
     *
     * @param request 查询请求
     * @return 查询结果
     */
    PLVodCommonResult queryByAuthorizationState(QueryByAuthorizationStateRequest request);

    /**
     * 按标题或分类查找视频
     *
     * @param request 查询请求
     * @return 查询结果
     */
    PLVodCommonResult queryByTitleOrClassification(QueryByTitleOrClassificationRequest request);

    /**
     * 按标签查找视频
     *
     * @param request 查询请求
     * @return 查询结果
     */
    PLVodCommonResult queryByTag(QueryByTagRequest request);

}
