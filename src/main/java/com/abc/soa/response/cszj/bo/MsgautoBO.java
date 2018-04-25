package com.abc.soa.response.cszj.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pan on 2017-08-03.
 */
public class MsgautoBO implements Serializable {

    private String id;
    private String toUserName;
    private String fromUserName;
    private long createTime;

    private String setting; //0:添加消息,1:回复消息,2:关键字消息
    private String keyString;//关键字

    private String msgType;//text,image,news
    private Integer sort;
    private String searchTp;



    //text
    private String content;

    //image
    private String mediaId;
    private String imgurl;

    //news
    private String newsId;
    private News news;

    private Date createDate;
    private Date lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSearchTp() {
        return searchTp;
    }

    public void setSearchTp(String searchTp) {
        this.searchTp = searchTp;
    }

    @Override
    public String toString() {
        return "MsgautoBO{" +
                "id='" + id + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", setting='" + setting + '\'' +
                ", keyString='" + keyString + '\'' +
                ", msgType='" + msgType + '\'' +
                ", sort=" + sort +
                ", searchTp='" + searchTp + '\'' +
                ", content='" + content + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", newsId='" + newsId + '\'' +
                ", news=" + news +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
