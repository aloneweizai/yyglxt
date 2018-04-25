package com.abc.dto.cms.content;

import java.util.List;

/**
 * Created by relic5 on 2017/6/11.
 */
public class ContentDto {

    private String contentId;

    private String channelId;

    private String typeId;

    private String modelId;

    private String siteId;

    private String topLevel;

    private String isRecommend;

    private String status;

    private String viewsDay;

    private String recommendLevel;

    private String contentType;

    private List<String> tagIds;        //关联的标签id

    private String userid;

    private String username;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(String topLevel) {
        this.topLevel = topLevel;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getViewsDay() {
        return viewsDay;
    }

    public void setViewsDay(String viewsDay) {
        this.viewsDay = viewsDay;
    }

    public String getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(String recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
