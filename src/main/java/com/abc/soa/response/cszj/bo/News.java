package com.abc.soa.response.cszj.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by pan on 2017-08-03.
 */
public class News implements Serializable {
    private String id;

    private int articleCount;//
    // 多条图文消息信息，默认第一个item为大图

    private List<Article> articles;
    private Date creatDate;
    private Date lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
