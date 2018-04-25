package com.abc.soa.request.system;

/**
 * Created by relic5 on 2017/6/6.
 */
public class ContentCriteria extends BasePaginationCriteria {

    private static final long serialVersionUID = 1L;

    private String title;

    private String topLevel;

    private String typeId;

    private String author;

    private String status;

    private String recommendLevel;

    private String channelId;

    private String username;

    private String siteId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(String topLevel) {
        this.topLevel = topLevel;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(String recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
