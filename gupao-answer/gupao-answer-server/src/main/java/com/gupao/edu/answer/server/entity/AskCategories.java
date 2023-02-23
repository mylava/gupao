package com.gupao.edu.answer.server.entity;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * (AskCategories)实体类
 *
 * @author makejava
 * @since 2020-03-20 22:16:19
 */
public class AskCategories implements Serializable {
    private static final long serialVersionUID = -98350198695680960L;
     @ApiModelProperty(value = " ")
    private Integer id;
     @ApiModelProperty(value = " ")
    private Integer parentId;
     @ApiModelProperty(value = " ")
    private Integer grade;
     @ApiModelProperty(value = " ")
    private String name;
     @ApiModelProperty(value = " ")
    private String icon;
     @ApiModelProperty(value = " ")
    private String slug;
     @ApiModelProperty(value = " ")
    private String type;
     @ApiModelProperty(value = " ")
    private Integer sort;
     @ApiModelProperty(value = " ")
    private String roleId;
     @ApiModelProperty(value = " ")
    private Byte status;
     @ApiModelProperty(value = " ")
    private Date createdAt;
     @ApiModelProperty(value = " ")
    private Date updatedAt;
    /**
    * 操作类型
    */ @ApiModelProperty(value = " 操作类型 ")
    private Byte optType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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