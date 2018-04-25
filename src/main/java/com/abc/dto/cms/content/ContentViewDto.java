package com.abc.dto.cms.content;

import java.util.Date;

/**
 * Created by relic5 on 2017/6/11.
 */
public class ContentViewDto {

    private String userid;

    private String username;
    /**
     * 内容ID
     **/
    private String contentId;

    /**
     * 栏目ID
     **/
    private String channelId;
    /**
     * 属性ID
     **/
    private String typeId;
    /**
     * 标题
     **/
    private String title;
    /**
     * 栏目名称
     **/
    private String channelName;
    /**
     * 固顶级别
     **/
    private String topLevel;

    private String typeName;
    /**
     * 作者
     **/
    private String author;
    /**
     * 总访问数
     **/
    private String views;
    /**
     * 发布日期
     **/
    private Date releaseDate;
    /**
     * 是否已生成静态页
     **/
    private String needRegenerate;
    /**
     * 站点Id**varchar(64)
     **/
    private String siteId;
    /**
     * 站点名称**varchar(100)
     **/
    private String siteName;
    /**
     * 域名**varchar(50)
     **/
    private String domain;
    /**
     * 站点路径**varchar(500)
     **/
    private String staticLink;

    /** (0:草稿;1:审核中;2:审核通过;3:回收站;4:投稿;5:归档) */
    private String status;
    /**
     * 站点状态
     **/
    private String siteStatus;
    /**
     * 推荐级别
     **/
    private String recommendLevel;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(String topLevel) {
        this.topLevel = topLevel;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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

    public String getNeedRegenerate() {
        return needRegenerate;
    }

    public void setNeedRegenerate(String needRegenerate) {
        this.needRegenerate = needRegenerate;
    }

    public String getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(String siteStatus) {
        this.siteStatus = siteStatus;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStaticLink() {
        return staticLink;
    }

    public void setStaticLink(String staticLink) {
        this.staticLink = staticLink;
    }
}
