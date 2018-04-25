package com.abc.soa.response.cszj.bo;

import java.io.Serializable;

/**
 * Created by pan on 2017-08-03.
 */
public class Article implements Serializable {

    private String newsId;

    private String title;

    private String description;

    private String picUrl;

    private String url;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
