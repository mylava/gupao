package com.gupao.edu.answer.client.form;

import com.alibaba.fastjson.JSONArray;

import java.util.Date;

public class SupportsForm {
    private Integer id;
    private String sessionId;
    private Integer userId;
    private Integer supportableId;
    private String supportableType;
    private Date createdAt;
    private Date updatedAt;
    private String optType;  //操作类型
    private Integer supports;  //获赞总数
    private JSONArray tagsList; //支持相关标签列表
    private String startTime;  //开始时间
    private String endTime;   //结束时间

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSupportableId() {
        return supportableId;
    }

    public void setSupportableId(Integer supportableId) {
        this.supportableId = supportableId;
    }

    public String getSupportableType() {
        return supportableType;
    }

    public void setSupportableType(String supportableType) {
        this.supportableType = supportableType;
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

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public Integer getSupports() {
        return supports;
    }

    public void setSupports(Integer supports) {
        this.supports = supports;
    }

    public JSONArray getTagsList() {
        return tagsList;
    }

    public void setTagsList(JSONArray tagsList) {
        this.tagsList = tagsList;
    }
}
