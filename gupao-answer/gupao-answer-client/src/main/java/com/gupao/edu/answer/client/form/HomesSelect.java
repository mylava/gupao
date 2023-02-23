package com.gupao.edu.answer.client.form;

public class HomesSelect {
    //栏目类型
    private String type;
    //排序类型
    private String orderType;
    //页码
    private int pageNum;
    //页面展示长度
    private int pageSize;
    //技术频道类型
    private String slug;
    //用户id
    private Integer userId;
    //技术类型id
    private Integer categoryId;

    public void getPage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "HomesSelect{" +
                "type='" + type + '\'' +
                ", orderType='" + orderType + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", slug='" + slug + '\'' +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }
}
