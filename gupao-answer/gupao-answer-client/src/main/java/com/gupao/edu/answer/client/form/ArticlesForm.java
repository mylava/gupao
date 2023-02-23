package com.gupao.edu.answer.client.form;

import com.gupao.edu.answer.client.dto.TagsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@ApiModel(value = "文章查询条件请求封装" ,description = "用于文章的各种查询")
public class ArticlesForm {
    @ApiModelProperty(value = "文章id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户logo")
    private String logo;

    @ApiModelProperty(value = "分类 id")
    private Integer categoryId;

    @ApiModelProperty(value = "标题 id")
    private String title;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "内容")
    private String content;
    private String views;
    @ApiModelProperty(value = "收藏id")
    private Integer collections;
    @ApiModelProperty(value = "社区id")
    private Integer comments;
    @ApiModelProperty(value = "点赞id")
    private Integer supports;
    @ApiModelProperty(value = "发布状态")
    private Byte status;
    @ApiModelProperty(value = "登录设备")
    private Byte device;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
    @ApiModelProperty(value = "更新时间")
    private String userName;
    //1 新增 2修改 3 删除 4 移动分类
    @ApiModelProperty(value = "类型",allowableValues = "1 新增 2修改 3 删除 4 移动分类")
    private String type;
    //分类名称
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("相似文章id ")
    private Integer articleId;
    @ApiModelProperty("相似问答id ")
    private Integer questionId;
    @ApiModelProperty(value = "区分是文章或者问题")
    private String sourceType;
    @ApiModelProperty(value = "话题名称 逗号分隔")
    private String tagName;
    //话题id   逗号分隔
    @ApiModelProperty(value = "话题id 逗号分隔")
    private String tagId;
    //评论/浏览数目
    @ApiModelProperty(value = "评论/浏览数目")
    private String answerViews;
    @ApiModelProperty(value = "新增标签列表")
    private List<TagsDTO> tagsList; //新增标签列表
    @ApiModelProperty(value = "标签json 字符串")
    private String tagsJsonStr;  //标签json 字符串
    @ApiModelProperty(value = "是否置顶")
    private Byte istop;  //是否置顶
    @ApiModelProperty(value = "置顶时间")
    private Date topAt;  //置顶时间
    @ApiModelProperty(value = "编辑器类型   ueditor  markdown")
    private String editorType;  //编辑器类型  ueditor  markdown
    @ApiModelProperty(value = "markdown内容 、 编辑内容")
    private String mdcontent; //markdown内容
    @ApiModelProperty(value = "文章公开区域")
    private String openArea;  //文章公开区域
    @ApiModelProperty(value = "反对数量")
    private Integer oppositions;  //反对数量
    @ApiModelProperty(value = "文章类型")
    private String articleType;   //文章类型
    @ApiModelProperty(value = "是否私密")
    private Byte confidential;  //是否私密
    @ApiModelProperty(value = "是否草稿")
    private Byte draft;  //是否草稿
    @ApiModelProperty(value = "已有标签id列表 ")
    private List<Integer> tagIdList;
    @ApiModelProperty(value = "排序时间维度 ",allowableValues = "month, total")
    private String statisticType;  //排序时间维度  month 月  total 总
    //开始时间
    @ApiModelProperty(value = "开始时间 ")
    private String startTime;
    //结束时间
    @ApiModelProperty(value = "结束时间 ")
    private String endTime;
    //排序方式  1:最新的   2:热门的  3:悬赏的   4:未回答的
    @ApiModelProperty(value = "排序方式 ",allowableValues = "1:最新的   2:热门的  3:悬赏的   4:未回答的")
    private String orderType;
    //页码
    @ApiModelProperty(value = "页码 ")
    private int pageNum;
    //页面展示长度
    @ApiModelProperty(value = "页面展示长度 ")
    private int pageSize;
    @ApiModelProperty(value = "标签ids  用-隔开 查询时使用")
    private String tagIds;  //标签ids  用-隔开
    @ApiModelProperty(value = "是否查询已删除值 true为不查询删除文章。可不填")
    private boolean backTrue ; //是否是后台查询
    @ApiModelProperty(value = "验证码")
    private String captcha; //验证码
    @ApiModelProperty(value = "验证码KEY (Cookies中返回)")
    private String captchaKey;  //验证码KEY (Cookies中返回)
    @ApiModelProperty(value = "vip标志")
    private boolean vipSign; //vip标志
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timingTime;  //定时任务执行时间
    @ApiModelProperty(value = "奖励G币数")
    private Integer coins;
    private String rewardType;

    public void getPage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Date getTimingTime() {
        return timingTime;
    }

    public void setTimingTime(Date timingTime) {
        this.timingTime = timingTime;
    }

    public boolean isVipSign() {
        return vipSign;
    }

    public void setVipSign(boolean vipSign) {
        this.vipSign = vipSign;
    }

    public String getStatisticType() {
        return statisticType;
    }

    public void setStatisticType(String statisticType) {
        this.statisticType = statisticType;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public boolean isBackTrue() {
        return backTrue;
    }

    public void setBackTrue(boolean backTrue) {
        this.backTrue = backTrue;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
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

    public List<TagsDTO> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagsDTO> tagsList) {
        this.tagsList = tagsList;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getTagsJsonStr() {
        return tagsJsonStr;
    }

    public void setTagsJsonStr(String tagsJsonStr) {
        this.tagsJsonStr = tagsJsonStr;
    }

    @Override
    public String toString() {
        return "ArticlesForm{" +
                "id=" + id +
                ", userId=" + userId +
                ", logo='" + logo + '\'' +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", views='" + views + '\'' +
                ", collections=" + collections +
                ", comments=" + comments +
                ", supports=" + supports +
                ", status=" + status +
                ", device=" + device +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", articleId=" + articleId +
                ", questionId=" + questionId +
                ", sourceType='" + sourceType + '\'' +
                ", tagName='" + tagName + '\'' +
                ", tagId='" + tagId + '\'' +
                ", answerViews='" + answerViews + '\'' +
                ", tagsList=" + tagsList +
                ", tagsJsonStr='" + tagsJsonStr + '\'' +
                ", istop=" + istop +
                ", topAt=" + topAt +
                ", editorType='" + editorType + '\'' +
                ", mdcontent='" + mdcontent + '\'' +
                ", openArea='" + openArea + '\'' +
                ", oppositions=" + oppositions +
                ", articleType='" + articleType + '\'' +
                ", confidential=" + confidential +
                ", draft=" + draft +
                ", tagIdList=" + tagIdList +
                ", statisticType='" + statisticType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", orderType='" + orderType + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", tagIds='" + tagIds + '\'' +
                ", backTrue=" + backTrue +
                ", captcha='" + captcha + '\'' +
                ", captchaKey='" + captchaKey + '\'' +
                ", vipSign=" + vipSign +
                ", timingTime=" + timingTime +
                ", coins=" + coins +
                ", rewardType='" + rewardType + '\'' +
                '}';
    }
}
