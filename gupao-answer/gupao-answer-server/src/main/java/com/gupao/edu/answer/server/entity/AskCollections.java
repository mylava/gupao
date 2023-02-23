package com.gupao.edu.answer.server.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 收藏表(AskCollections)实体类
 *
 * @author makejava
 * @since 2020-03-18 22:10:19
 */
public class AskCollections implements Serializable {
    private static final long serialVersionUID = 489914880778473987L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer sourceId;
    
    private String sourceType;
    
    private String subject;
    
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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