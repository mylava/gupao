package com.gupao.edu.answer.client.dto;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ArticlesDTO {
    private Integer id;
    private Integer userId;
    private String logo;
    private Integer categoryId;
    private String title;
    private String summary;
    private String content;
    private Integer views;
    private Integer collections;
    private Integer comments;
    private Integer supports;
    private Byte status;
    private Byte device;
    private Date createdAt;
    private Date updatedAt;
    private Byte istop;  //是否置顶
    private Date topAt;  //置顶时间
    private Byte optType;  //修改类型  1：有效 0：无效
    //用户名
    private String userName;
    //分类名称
    private String categoryName;
    //头像url
    private String avatarUrl;
    //区分是文章或者问题
    private String sourceType;
    //话题id，name Array
    private JSONArray tagsList;
    //评论/浏览数目
    private String answerViews;
    private Integer totalCount; //当月发表文章总数
    private String qrcodeUrl;  //支付二维码

    private String editorType;  //编辑器类型  ueditor  markdown
    private String mdcontent; //markdown内容

    private boolean vipSign; //vip标志

    private String openArea;  //文章公开区域
    private Integer oppositions;  //反对数量

    private Integer recommendSign;  //推荐状态  1：推荐 0：未操作 -1：不推荐

    private String articleType;   //文章类型
    private Byte confidential;  //是否私密
    private Byte draft;  //是否草稿
    private Date timingTime;  //定时任务执行时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getTimingTime() {
        return timingTime;
    }

    public void setTimingTime(Date timingTime) {
        this.timingTime = timingTime;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public Byte getConfidential() {
        return confidential;
    }

    public void setConfidential(Byte confidential) {
        this.confidential = confidential;
    }

    public Byte getDraft() {
        return draft;
    }

    public void setDraft(Byte draft) {
        this.draft = draft;
    }

    public Integer getRecommendSign() {
        return recommendSign;
    }

    public void setRecommendSign(Integer recommendSign) {
        this.recommendSign = recommendSign;
    }

    public Integer getOppositions() {
        return oppositions;
    }

    public void setOppositions(Integer oppositions) {
        this.oppositions = oppositions;
    }

    public String getOpenArea() {
        return openArea;
    }

    public void setOpenArea(String openArea) {
        this.openArea = openArea;
    }

    public boolean isVipSign() {
        return vipSign;
    }

    public void setVipSign(boolean vipSign) {
        this.vipSign = vipSign;
    }

    public String getEditorType() {
        return editorType;
    }

    public void setEditorType(String editorType) {
        this.editorType = editorType;
    }

    public String getMdcontent() {
        return mdcontent;
    }

    public void setMdcontent(String mdcontent) {
        this.mdcontent = mdcontent;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCollections() {
        return collections;
    }

    public void setCollections(Integer collections) {
        this.collections = collections;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getSupports() {
        return supports;
    }

    public void setSupports(Integer supports) {
        this.supports = supports;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getAnswerViews() {
        return answerViews;
    }

    public void setAnswerViews(String answerViews) {
        this.answerViews = answerViews;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public JSONArray getTagsList() {
        return tagsList;
    }

    public void setTagsList(JSONArray tagsList) {
        this.tagsList = tagsList;
    }

    public Byte getIstop() {
        return istop;
    }

    public void setIstop(Byte istop) {
        this.istop = istop;
    }

    public Date getTopAt() {
        return topAt;
    }

    public void setTopAt(Date topAt) {
        this.topAt = topAt;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Byte getOptType() {
        return optType;
    }

    public void setOptType(Byte optType) {
        this.optType = optType;
    }
}
