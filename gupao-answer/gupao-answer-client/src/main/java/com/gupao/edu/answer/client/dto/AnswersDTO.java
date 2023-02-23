package com.gupao.edu.answer.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class AnswersDTO {

    private Integer id;
    private String questionTitle;
    private Integer questionId;
    private Integer userId;
    private String content;
    private Integer supports;
    private Integer oppositions;
    private Integer comments;
    private Byte device;
    private Byte status;
    private Date adoptedAt;
    private Date createdAt;
    private Date updatedAt;
    private String userName;
    private String avatarUrl;  //头像url
    private Integer totalCount; //当月回答总数
    private boolean hasSupport;  //返回是否点赞标志
    private boolean hasOpposition;  //返回反对标志
    private String qrcodeUrl;  //支付二维码
    private Integer adoptionRate;  //采纳率
    private String editorType;  //编辑器类型  ueditor  markdown
    private String mdcontent; //markdown内容
    private boolean vipSign; //vip标志
    private List<CommentsDTO> replyList;
    private long replyTotal;

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

    public Integer getAdoptionRate() {
        return adoptionRate;
    }

    public void setAdoptionRate(Integer adoptionRate) {
        this.adoptionRate = adoptionRate;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public boolean isHasSupport() {
        return hasSupport;
    }

    public void setHasSupport(boolean hasSupport) {
        this.hasSupport = hasSupport;
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

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    public Date getAdoptedAt() {
        return adoptedAt;
    }

    public void setAdoptedAt(Date adoptedAt) {
        this.adoptedAt = adoptedAt;
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

    public boolean isHasOpposition() {
        return hasOpposition;
    }

    public void setHasOpposition(boolean hasOpposition) {
        this.hasOpposition = hasOpposition;
    }
}
