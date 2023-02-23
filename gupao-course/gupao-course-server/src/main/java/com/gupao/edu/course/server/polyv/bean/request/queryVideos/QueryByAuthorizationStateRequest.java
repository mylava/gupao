package com.gupao.edu.course.server.polyv.bean.request.queryVideos;


import com.gupao.edu.course.server.polyv.bean.request.PLVodBaseRequest;
import com.gupao.edu.course.server.polyv.enumeration.ErrorCodeEnum;

/**
 * 根据授权播放开关状态查询视频请求类
 *
 * @author zhangpan@centaline.com
 * createTime 2020/5/6
 */
public class QueryByAuthorizationStateRequest extends PLVodBaseRequest {

    /**
     * <pre>
     * 字段名：授权播放开关状态
     * 变量名：playauth
     * 是否必填：是
     * </pre>
     */
    private byte playauth;

    /**
     * <pre>
     * 字段名：每页数量
     * 变量名：numPerPage
     * 是否必填：否
     * </pre>
     */
    private int numPerPage = ErrorCodeEnum.DEFAULT.getCode();
    /**
     * 设置预览时长 单位秒
     */
    private int duration;
    /**
     * 视频id
     */
    private String videoId;
    /**
     * 当前时间戳
     */
    private long ts;
    /**
     * 用户uerseUniquecode
     */
    private String  viewerId;
    /**
     * 视频vid
     */
    private String vid;
    /**
     * <pre>
     * 字段名：页数
     * 变量名：pageNum
     * 是否必填：否
     * </pre>
     */
    private int pageNum = ErrorCodeEnum.DEFAULT.getCode();

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public QueryByAuthorizationStateRequest(String userId, String secretKey, byte playauth) {
        super(userId, secretKey);
        this.playauth = playauth;
    }
    public QueryByAuthorizationStateRequest(String userId, String secretKey, String videoId,long ts,String viewerId) {
        super(userId,secretKey);
        this.videoId = videoId;
        this.ts = ts;
        this.viewerId = viewerId;
    }
    public QueryByAuthorizationStateRequest(String userId, String secretKey, int duration,String vid) {
        super(userId, secretKey);
        this.vid = vid;
        this.duration = duration;
    }
    public QueryByAuthorizationStateRequest(String userId, String secretKey, byte playauth, int numPerPage) {
        super(userId, secretKey);
        this.playauth = playauth;
        this.numPerPage = numPerPage;
    }

    public QueryByAuthorizationStateRequest(String userId, String secretKey, byte playauth, int numPerPage, int pageNum) {
        super(userId, secretKey);
        this.playauth = playauth;
        this.numPerPage = numPerPage;
        this.pageNum = pageNum;
    }

    public byte getPlayauth() {
        return playauth;
    }

    public void setPlayauth(byte playauth) {
        this.playauth = playauth;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getViewerId() {
        return viewerId;
    }

    public void setViewerId(String viewerId) {
        this.viewerId = viewerId;
    }

    @Override
    public String toString() {
        return "QueryByAuthorizationStateRequest{" +
                "playauth=" + playauth +
                ", numPerPage=" + numPerPage +
                ", pageNum=" + pageNum +
                ", userId='" + userId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", ptime=" + ptime +
                ", sign='" + sign + '\'' +
                '}';
    }
}
