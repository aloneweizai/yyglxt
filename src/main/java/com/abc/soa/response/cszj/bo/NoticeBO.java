package com.abc.soa.response.cszj.bo;

import java.io.Serializable;

/**
 * Created by relic5 on 2017/6/19.
 */
public class NoticeBO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    //通告标题
    private String title;

    //作者
    private String author;

    //通告内容
    private String content;

    //状态
    private String status;

    //创建时间
    private String createTime;

    //修改时间
    private String lastUpdate;

    //通告来源
    private String comefrom;

    //发布时间
    private String releaseTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}
