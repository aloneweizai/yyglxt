package com.abc.dto.cms.staticpage;

import java.io.Serializable;

/**
 * Created by zhouzhi on 2017-07-12.
 */
public class ContentPreNextBo implements Serializable {
    private String contentId;
    private String channelPath;
    private String title;
    private String sitePath;
    private String upordown;
    private String domain;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getChannelPath() {
        return channelPath;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSitePath() {
        return sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }

    public String getUpordown() {
        return upordown;
    }

    public void setUpordown(String upordown) {
        this.upordown = upordown;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
