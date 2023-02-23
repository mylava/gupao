package com.gupao.edu.course.server.polyv.constant;

/**
 * 点播接口常量类
 *
 * @author lamdaer
 * createTime 2020/05/03
 */
public class ApiConstants {

    /**
     * 获取用户空间及流量情况
     */
    public static final String GET_USER_SPACE_TRAFFIC_USAGE = "http://api.polyv.net/v2/user/{userid}/main";

    /**
     * 根据授权播放开关状态查询视频
     */
    public static final String QUERY_VIDEO_BY_AUTHORIZATION_STATE = "http://api.polyv.net/v2/video/{userid}/list";

    /**
     * 按标题或分类查找视频
     */
    public static final String QUERY_VIDEO_BY_TITLE_OR_CLASSIFICATION = "http://api.polyv.net/v2/video/{userid}/search";

    /**
     * 按标签查找视频
     */
    public static final String QUERY_BY_TAG = "http://api.polyv.net/v2/video/{userid}/search";

    /**
     * 查询视频播放量统计数据接口
     */
    public static final String VIDEO_VIEW = "http://api.polyv.net/v2/videoview/{userid}";

    /**
     * 查询视频的播放时长统计数据
     */
    public static final String VIDEO_VIEW_TIME = "https://api.polyv.net/v2/play-duration/{userid}";
    /**
     * 查询视频的观众量统计
     */
    public static final String VIDEO_VIEW_NUM = "https://api.polyv.net/v2/data/visitor/{userid}";

    /**
     * 获取视频观看完整度
     */

    public static final String VIDEO_VIEW_WANZHENGDU = "https://api.polyv.net/v2/video/engagement/{userid}/get";

    /**
     * 设置视频的播放预览时长
     */

    public static final String VIDEO_PREVIEW_DURATION = "http://api.polyv.net/v2/video/{userid}/set-preview-duration";


    /**
     * 批量修改视频的授权方式
     */
    public static final String VIDEO_HLSLEVEL = "http://api.polyv.net/v2/config/{userid}/hlslevel";

    /**
     * 编辑单个视频的信息
     */
    public static final String VIDEO_INFO = "http://api.polyv.net/v2/video/{userid}/video-info";

    /**
     * 创建playSafe Token
     */
    public static final String VIDEO_PLAYSAFETOKEN = "https://hls.videocc.net/service/v1/token";

    /**
     * 获取视频播放预览时长接口
     */
    public static final String VIDEO_GETPREVIEW_DURATION = "http://api.polyv.net/v2/video/{userid}/get-preview-duration";

    /**
     * 获取单个视频的首图
     */
    public static final String VIDEO_GET_IMAGE = "http://api.polyv.net/v2/video/{userid}/get-image";

    /**
     * 上传视频
     */
    public static final String VIDEO_UPLOAD = "http://v.polyv.net/uc/services/rest?method=uploadfile";
    private ApiConstants() {
    }

}
