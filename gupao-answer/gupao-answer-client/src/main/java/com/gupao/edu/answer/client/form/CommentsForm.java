package com.gupao.edu.answer.client.form;

import java.util.Date;

public class CommentsForm {

    private Integer id;
    private Integer userId;
    private String content;
    private Integer sourceId;
    private String sourceType;
    private Integer toUserId;
    private Byte status;
    private Integer supports;
    private Byte device;
    private Date createdAt;
    private Date updatedAt;
    private String userName;
    //1 新增 2修改 3 删除 4 移动分类 5 移动分类
    private String type;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //页码
    private int pageNum;
    //页面展示长度
    private int pageSize;
    //评论回复页码
    private int replyPageNum;
    //评论回复页面展示长度
    private int replyPageSize;
    //是否是后台查询
    private boolean backTrue;

    //排序方式  default:默认  new:最新的
    private String orderType;

    public void getPage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
    }

    public void getReplyPage() {
        replyPageNum = replyPageNum>0?replyPageNum:1;
        replyPageSize = replyPageSize>0?replyPageSize:2;
    }

    public int getReplyPageNum() {
        return replyPageNum;
    }

    public void setReplyPageNum(int replyPageNum) {
        this.replyPageNum = replyPageNum;
    }

    public int getReplyPageSize() {
        return replyPageSize;
    }

    public void setReplyPageSize(int replyPageSize) {
        this.replyPageSize = replyPageSize;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isBackTrue() {
        return backTrue;
    }

    public void setBackTrue(boolean backTrue) {
        this.backTrue = backTrue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSupports() {
        return supports;
    }

    public void setSupports(Integer supports) {
        this.supports = supports;
    }

    public Byte getDevice() {
        return device;
    }

    public void setDevice(Byte device) {
        this.device = device;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CommentsForm{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", sourceId=" + sourceId +
                ", sourceType='" + sourceType + '\'' +
                ", toUserId=" + toUserId +
                ", status=" + status +
                ", supports=" + supports +
                ", device=" + device +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", replyPageNum=" + replyPageNum +
                ", replyPageSize=" + replyPageSize +
                ", backTrue=" + backTrue +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
