package com.gupao.edu.answer.client.form;

import java.util.Arrays;
import java.util.Date;

public class CategoriesForm {

    //页码
    private int pageNum;
    //页面展示长度
    private int pageSize;
    //是否是后台查询
    private boolean backTrue;
    private Integer id;
    private Integer parentId;
    private Integer grade;
    private String name;
    private String icon;
    private String slug;
    private String[] types;
    private Integer sort;
    private String roleId;
    private Byte status;
    private Date createdAt;
    private Date updatedAt;
    //操作类型add：新增update：修改 或者允许显示的栏目类别
    private String type;
    public void getPage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
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

    @Override
    public String toString() {
        return "CategoriesForm{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", backTrue=" + backTrue +
                ", id=" + id +
                ", parentId=" + parentId +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", slug='" + slug + '\'' +
                ", types=" + Arrays.toString(types) +
                ", sort=" + sort +
                ", roleId='" + roleId + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", type='" + type + '\'' +
                '}';
    }
}
