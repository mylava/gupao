package com.gupao.edu.answer.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class CommentsDTO {

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
    private String toUserName;
    //头像url
    private String avatarUrl;
    //返回是否点赞标志
    private boolean hasSupport;
    //评论回复列表
    private List<CommentsDTO> replyList;
    //评论回复总数
    private long replyTotal;

    private boolean vipSign; //vip标志

    public boolean isVipSign() {
        return vipSign;
    }

    public void setVipSign(boolean vipSign) {
        this.vipSign = vipSign;
    }

    public List<CommentsDTO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<CommentsDTO> replyList) {
        this.replyList = replyList;
    }

    public long getReplyTotal() {
        return replyTotal;
    }

    public void setReplyTotal(long replyTotal) {
        this.replyTotal = replyTotal;
    }

    public boolean isHasSupport() {
        return hasSupport;
    }

    public void setHasSupport(boolean hasSupport) {
        this.hasSupport = hasSupport;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
}
