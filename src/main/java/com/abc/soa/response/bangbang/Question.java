package com.abc.soa.response.bangbang;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2017/10/11 15:26
 */
public class Question {

    /****varchar(64)**/
    private String id;

    /**用户ID**varchar(64)**/
    private String userId;

    /**问题**varchar(300)**/
    private String title;

    /**问题详情**longtext**/
    private String detail;

    /**问题状态**varchar(20)**/
    private String status;

    /**创建时间**datetime**/
    private java.util.Date createTime;

    /**修改时间**datetime**/
    private java.util.Date lastUpdate;

    /**悬赏积分**int(11)**/
    private Integer points;

    /**是否解决：0/1**tinyint(4)**/
    private Integer isSolve;

    /**问题分类**varchar(30)**/
    private String classifyCode;

    /**浏览量**int(11)**/
    private Integer browseNum;

    /**是否推荐**/
    private Boolean isRecommend;

    /**推荐创建时间**/
    private java.util.Date recommendTime;

    /**标签**varchar(1000)**/
    private String tag;

    /**ip**varchar(45)**/
    private String ip;

    /****varchar(64)**/
    private String factionId;

    /**回复数**int(11)**/
    private Integer answerNum;

    /**点赞数**int(11)**/
    private Integer likeNum;

    /**评论数**/
    private Integer commentNum;

    public String getId() {
        return id;
    }

    public Question setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Question setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public Question setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Question setStatus(String status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Question setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Question setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public Integer getPoints() {
        return points;
    }

    public Question setPoints(Integer points) {
        this.points = points;
        return this;
    }

    public Integer getIsSolve() {
        return isSolve;
    }

    public Question setIsSolve(Integer isSolve) {
        this.isSolve = isSolve;
        return this;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public Question setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
        return this;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public Question setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
        return this;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public Question setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
        return this;
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public Question setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Question setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public Question setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getFactionId() {
        return factionId;
    }

    public Question setFactionId(String factionId) {
        this.factionId = factionId;
        return this;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public Question setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
        return this;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public Question setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
        return this;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public Question setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
        return this;
    }
}
