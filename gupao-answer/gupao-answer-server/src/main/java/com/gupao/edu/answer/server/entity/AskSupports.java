package com.gupao.edu.answer.server.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 点赞表(AskSupports)实体类
 *
 * @author makejava
 * @since 2020-03-18 21:58:10
 */
public class AskSupports implements Serializable {
    private static final long serialVersionUID = -29847624441685516L;
    
    private Integer id;
    
    private String sessionId;
    
    private Integer userId;
    
    private Integer supportableId;
    
    private String supportableType;
    
    private Date createdAt;
    
    private Date updatedAt;
    /**
    * 操作类型
    */
    private Byte optType;


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

    public Byte getOptType() {
        return optType;
    }

    public void setOptType(Byte optType) {
        this.optType = optType;
    }

}