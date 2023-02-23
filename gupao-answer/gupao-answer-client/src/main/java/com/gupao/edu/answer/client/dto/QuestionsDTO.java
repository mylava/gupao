package com.gupao.edu.answer.client.dto;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;
import java.util.Date;

public class QuestionsDTO {

    private Integer id;

    private Integer userId;

    private Integer categoryId;

    private String title;

    private Short price;

    private Byte hide;

    private Integer answers;

    private Integer views;

    private Integer followers;

    private Integer collections;

    private Integer comments;

    private Byte device;

    private Byte status;

    private Date createdAt;

    private Date updatedAt;

    private String description;

    private String userName;
    private Byte istop;  //是否置顶
    private Date topAt;  //置顶时间
    //头像url
    private String avatarUrl;
    //话题id，name Array
    private JSONArray tagsList;
    //区分是文章或者问题
    private String sourceType;
    //分类名称
    private String categoryName;
    private String answerViews;
    private Byte optType;
    //是否已经回答
    private boolean hasAnswer;
    private String editorType;  //编辑器类型  ueditor  markdown
    private String mddescription; //markdown内容

    private boolean vipSign; //vip标志

    private Integer supports;  //支持数量
    private Integer oppositions;  //反对数量

    private Integer recommendSign;  //推荐状态  1：推荐 0：未操作 -1：不推荐

    private String openArea;  //开放区域
    private Byte draft;  //是否草稿
    private Integer toUserId;   //邀请用户id
    private String toUserName;  //邀请人姓名
    private boolean adoptSign;  //是否可采纳 true:可以  false：不可以
    private String levelType;  //邀请用户类型
    private String deductType;  //邀请扣除类型
    private Date timingTime;  //定时任务执行时间
    private Integer totalCount; //当月发表提问总数

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getTimingTime() {
        return timingTime;
    }

    public void setTimingTime(Date timingTime) {
        this.timingTime = timingTime;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getDeductType() {
        return deductType;
    }

    public void setDeductType(String deductType) {
        this.deductType = deductType;
    }

    public boolean isAdoptSign() {
        Date now = new Date();
        if (now.after(getDateAddWeek(getCreatedAt(), 1))){
            return true;
        }
        return false;
    }
    public Date getDateAddWeek(Date date,int num){
        Calendar cal = Calendar.getInstance();
        //设置起时间
        if(date == null){
            cal.setTime(new Date());
        }else{
            cal.setTime(date);
        }
        cal.add(Calendar.DATE,7*num);
        return cal.getTime();
    }

    public void setAdoptSign(boolean adoptSign) {
        this.adoptSign = adoptSign;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Byte getDraft() {
        return draft;
    }

    public void setDraft(Byte draft) {
        this.draft = draft;
    }

    public String getOpenArea() {
        return openArea;
    }

    public void setOpenArea(String openArea) {
        this.openArea = openArea;
    }

    public Integer getRecommendSign() {
        return recommendSign;
    }

    public void setRecommendSign(Integer recommendSign) {
        this.recommendSign = recommendSign;
    }

    public Integer getSupports() {
        return supports;
    }

    public void setSupports(Integer supports) {
        this.supports = supports;
    }

    public Integer getOppositions() {
        return oppositions;
    }

    public void setOppositions(Integer oppositions) {
        this.oppositions = oppositions;
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

    public String getMddescription() {
        return mddescription;
    }

    public void setMddescription(String mddescription) {
        this.mddescription = mddescription;
    }

    public boolean isHasAnswer() {
        return hasAnswer;
    }

    public void setHasAnswer(boolean hasAnswer) {
        this.hasAnswer = hasAnswer;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Short getPrice() {
        return price;
    }

    public void setPrice(Short price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Byte getHide() {
        return hide;
    }

    public void setHide(Byte hide) {
        this.hide = hide;
    }

    public Integer getAnswers() {
        return answers;
    }

    public void setAnswers(Integer answers) {
        this.answers = answers;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
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

    public Byte getDevice() {
        return device;
    }

    public void setDevice(Byte device) {
        this.device = device;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUserName() {
        return userName;
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

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Byte getOptType() {
        return optType;
    }

    public void setOptType(Byte optType) {
        this.optType = optType;
    }
}
