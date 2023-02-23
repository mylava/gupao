package com.gupao.edu.answer.client.dto;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AuthenticationsDTO {

    private Integer userId;

    private Integer categoryId;

    private String categoryName;

    private String realName;

    private Short province;

    private String title;

    private Byte gender;

    private Short city;

    private String idCard;

    private String idCardImage;

    private String idCardImageUrl;

    private String skill;

    private String skillImage;

    private String skillImageUrl;

    private String failedReason;

    private Byte status;

    private Date createdAt;

    private Date updatedAt;

    private Date recommendAt;

    private String description;

    private JSONArray tagsArray;
    //用户名
    private String userName;
    //头像url
    private String avatarUrl;

    private boolean vipSign; //vip标志
    //是否关注  true ：关注  false ：未关注
    private boolean hasAttention;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isHasAttention() {
        return hasAttention;
    }

    public void setHasAttention(boolean hasAttention) {
        this.hasAttention = hasAttention;
    }

    public boolean isVipSign() {
        return vipSign;
    }

    public void setVipSign(boolean vipSign) {
        this.vipSign = vipSign;
    }

    public String getIdCardImageUrl() {
        return idCardImageUrl;
    }

    public void setIdCardImageUrl(String idCardImageUrl) {
        this.idCardImageUrl = idCardImageUrl;
    }

    public String getSkillImageUrl() {
        return skillImageUrl;
    }

    public void setSkillImageUrl(String skillImageUrl) {
        this.skillImageUrl = skillImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public JSONArray getTagsArray() {
        return tagsArray;
    }

    public void setTagsArray(JSONArray tagsArray) {
        this.tagsArray = tagsArray;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Short getProvince() {
        return province;
    }

    public void setProvince(Short province) {
        this.province = province;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Short getCity() {
        return city;
    }

    public void setCity(Short city) {
        this.city = city;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getIdCardImage() {
        return idCardImage;
    }

    public void setIdCardImage(String idCardImage) {
        this.idCardImage = idCardImage == null ? null : idCardImage.trim();
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }

    public String getSkillImage() {
        return skillImage;
    }

    public void setSkillImage(String skillImage) {
        this.skillImage = skillImage == null ? null : skillImage.trim();
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason == null ? null : failedReason.trim();
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

    public Date getRecommendAt() {
        return recommendAt;
    }

    public void setRecommendAt(Date recommendAt) {
        this.recommendAt = recommendAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "AuthenticationsDTO{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                ", realName='" + realName + '\'' +
                ", province=" + province +
                ", title='" + title + '\'' +
                ", gender=" + gender +
                ", city=" + city +
                ", idCard='" + idCard + '\'' +
                ", idCardImage='" + idCardImage + '\'' +
                ", idCardImageUrl='" + idCardImageUrl + '\'' +
                ", skill='" + skill + '\'' +
                ", skillImage='" + skillImage + '\'' +
                ", skillImageUrl='" + skillImageUrl + '\'' +
                ", failedReason='" + failedReason + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", recommendAt=" + recommendAt +
                ", description='" + description + '\'' +
                ", tagsArray=" + tagsArray +
                ", userName='" + userName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", vipSign=" + vipSign +
                ", hasAttention=" + hasAttention +
                '}';
    }
}
