package com.gupao.edu.course.server.polyv.service.impl;


import com.gupao.edu.course.server.polyv.bean.request.queryVideos.QueryByAuthorizationStateRequest;
import com.gupao.edu.course.server.polyv.bean.request.queryVideos.QueryByTagRequest;
import com.gupao.edu.course.server.polyv.bean.request.queryVideos.QueryByTitleOrClassificationRequest;
import com.gupao.edu.course.server.polyv.bean.result.PLVodCommonResult;
import com.gupao.edu.course.server.polyv.constant.ApiConstants;
import com.gupao.edu.course.server.polyv.service.PLVodRequestAbstractService;
import com.gupao.edu.course.server.polyv.service.QueryVideoService;

/**
 * 查找视频 Service 实现类
 *
 * @author zhangpan@centaline.com
 * createTime 2020/5/6
 */
public class QueryVideoServiceImpl extends PLVodRequestAbstractService implements QueryVideoService {

    @Override
    public PLVodCommonResult queryByAuthorizationState(QueryByAuthorizationStateRequest request) {
        PLVodCommonResult result = getRequest(ApiConstants.QUERY_VIDEO_BY_AUTHORIZATION_STATE.replace("{userid}", request.getUserId()), request.getParams());
        return result;
    }

    @Override
    public PLVodCommonResult queryByTitleOrClassification(QueryByTitleOrClassificationRequest request) {
        PLVodCommonResult result = getRequest(ApiConstants.QUERY_VIDEO_BY_TITLE_OR_CLASSIFICATION.replace("{userid}", request.getUserId()), request.getParams());
        return result;
    }

    @Override
    public PLVodCommonResult queryByTag(QueryByTagRequest request) {
        PLVodCommonResult result = getRequest(ApiConstants.QUERY_BY_TAG.replace("{userid}", request.getUserId()), request.getParams());
        return result;
    }
    public PLVodCommonResult videoView(QueryByAuthorizationStateRequest request) {
        PLVodCommonResult result = getRequest(ApiConstants.VIDEO_VIEW.replace("{userid}", request.getUserId()), request.getParams());
        return result;
    }
    public PLVodCommonResult videoViewTime(QueryByAuthorizationStateRequest request) {
        PLVodCommonResult result = getRequest(ApiConstants.VIDEO_VIEW_TIME.replace("{userid}", request.getUserId()), request.getParams());
        return result;
    }
    public PLVodCommonResult previewDuration(QueryByAuthorizationStateRequest request) {
        PLVodCommonResult result = getRequest(ApiConstants.VIDEO_PLAYSAFETOKEN, request.getParams());
        return result;
    }
    public PLVodCommonResult upload(QueryByAuthorizationStateRequest request) {
        PLVodCommonResult result = getRequest(ApiConstants.VIDEO_UPLOAD, request.getParams());
        return result;
    }
}
