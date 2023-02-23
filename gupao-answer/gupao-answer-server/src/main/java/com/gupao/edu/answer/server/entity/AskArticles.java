package com.gupao.edu.answer.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (AskArticles)实体类
 *
 * @author makejava
 * @since 2020-03-19 17:59:33
 */
@ApiModel("文章")
public class AskArticles implements Serializable {
    private static final long serialVersionUID = 695057783307737312L;
     @ApiModelProperty(value = " 文章Id")
     @TableId
    private Integer id;
     @ApiModelProperty(value = " 会员id")
    private Integer userId;
     @ApiModelProperty(value = " logo链接")
    private String logo;
     @ApiModelProperty(value = " 分类 ")
    private Integer categoryId;
     @ApiModelProperty(value = " 标题 ")
    private String title;
     @ApiModelProperty(value = " 摘要 ")
    private String summary;
     @ApiModelProperty(value = " 内容 ")
    private String content;
     @ApiModelProperty(value = " 阅读数 ")
    private Integer views;
     @ApiModelProperty(value = " 收藏数 ")
    private Integer collections;
     @ApiModelProperty(value = " 评论数 ")
    private Integer comments;
     @ApiModelProperty(value = " 点赞数 ")
    private Integer supports;
     @ApiModelProperty(value = " 状态 ")
    private Byte status;
     @ApiModelProperty(value = " 设备")
    private Byte device;
     @ApiModelProperty(value = " 创建时间 ")
    private Date createdAt;
     @ApiModelProperty(value = " 更新时间 ")
    private Date updatedAt;
    /**
    * 操作类型
    */ @ApiModelProperty(value = " 操作类型 ")
    private Byte optType;
    /**
    * 是否置顶(1:置顶，0:否)
    */ @ApiModelProperty(value = " 是否置顶(1:置顶，0:否) ")
    private Byte istop;
    /**
    * 置顶时间
    */ @ApiModelProperty(value = " 置顶时间 ")
    private Date topAt;
    /**
    * 编辑器类型  ueditor  markdown
    */ @ApiModelProperty(value = " 编辑器类型  ueditor  markdown ")
    private String editortype;
     @ApiModelProperty(value = " markdowncontent ")
    private String mdcontent;
    /**
    * 公开区域(all：所有，vip：对vip)
    */ @ApiModelProperty(value = " 公开区域(all：所有，vip：对vip) ")
    private String openArea;
    /**
    * 不推荐数量
    */ @ApiModelProperty(value = " 不推荐数量 ")
    private Integer oppositions;
    /**
    * 文章类型（ origina：原创，repost：转载，translated：翻译）
    */ @ApiModelProperty(value = " 文章类型（ origina：原创，repost：转载，translated：翻译） ")
    private String articleType;
    /**
    * 是否私密文章（0：否，1：是）
    */ @ApiModelProperty(value = " 是否私密文章（0：否，1：是） ")
    private Byte confidential;
    /**
    * 是否保存为草稿（0：否，1：是）
    */ @ApiModelProperty(value = " 是否保存为草稿（0：否，1：是） ")
    private Byte draft;
    /**
    * 后台爬取来源文章id
    */ @ApiModelProperty(value = " 后台爬取来源文章id 用来关联评论")
    private Integer sourceId;
    /**
    * 后台爬取来源文章类型(language:编程语言、soft:软件更新、generic：综合咨询、industry：行业咨询)
    */ @ApiModelProperty(value = " 后台爬取来源文章类型(language:编程语言、soft:软件更新、generic：综合咨询、industry：行业咨询) " , allowableValues = "App\\Models\\Article")
    private String sourceType;
    @ApiModelProperty(value = "标签列表")
    private List<String> tagList;

    //自定义标签名称
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

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
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

    public String getEditortype() {
        return editortype;
    }

    public void setEditortype(String editortype) {
        this.editortype = editortype;
    }

    public String getMdcontent() {
        return mdcontent;
    }

    public void setMdcontent(String mdcontent) {
        this.mdcontent = mdcontent;
    }

    public String getOpenArea() {
        return openArea;
    }

    public void setOpenArea(String openArea) {
        this.openArea = openArea;
    }

    public Integer getOppositions() {
        return oppositions;
    }

    public void setOppositions(Integer oppositions) {
        this.oppositions = oppositions;
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

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}