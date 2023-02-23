package com.gupao.edu.answer.client.form;

import com.alibaba.fastjson.JSONArray;

import java.util.Date;

public class AnswersForm {
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
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //是否是后台查询
    private boolean backTrue;
    //页码
    private int pageNum;
    //页面展示长度
    private int pageSize;

    private String adoptStartTime;   //采纳开始时间
    private String adoptEndTime;   //采纳结束时间

    //排序方式  default:默认  new:最新的
    private String orderType;

    private JSONArray tagsList; //回答相关标签列表
    private Byte followed; //回答问题是否选择关注（1：关注，0：否）

    //1 新增 2修改 3 删除 4 移动分类
    private String type;

    private String editorType;  //编辑器类型  ueditor  markdown
    private String mdcontent; //markdown内容

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

    public void getPage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public JSONArray getTagsList() {
        return tagsList;
    }

    public void setTagsList(JSONArray tagsList) {
        this.tagsList = tagsList;
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

    public Date getAdoptedAt() {
        return adoptedAt;
    }

    public void setAdoptedAt(Date adoptedAt) {
        this.adoptedAt = adoptedAt;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Byte getFollowed() {
        return followed;
    }

    public void setFollowed(Byte followed) {
        this.followed = followed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public boolean isBackTrue() {
        return backTrue;
    }

    public void setBackTrue(boolean backTrue) {
        this.backTrue = backTrue;
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

    public String getAdoptStartTime() {
        return adoptStartTime;
    }

    public void setAdoptStartTime(String adoptStartTime) {
        this.adoptStartTime = adoptStartTime;
    }

    public String getAdoptEndTime() {
        return adoptEndTime;
    }

    public void setAdoptEndTime(String adoptEndTime) {
        this.adoptEndTime = adoptEndTime;
    }

    @Override
    public String toString() {
        return "AnswersForm{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionId=" + questionId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", supports=" + supports +
                ", oppositions=" + oppositions +
                ", comments=" + comments +
                ", device=" + device +
                ", status=" + status +
                ", adoptedAt=" + adoptedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userName='" + userName + '\'' +
                ", tagsList=" + tagsList +
                ", followed=" + followed +
                ", type='" + type + '\'' +
                '}';
    }
}
