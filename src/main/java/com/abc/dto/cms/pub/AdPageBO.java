package com.abc.dto.cms.pub;



/**
 * ���ͼƬ����
 * User: yuanluo<435720953@qq.com.com>
 * Date: 2017-07-26
 * Time: 11:06
 */
public class AdPageBO {

    private String id;

    private String name;
    private String url;
    private String link;



    private String style;

    private Integer sort;
    private Boolean showName;
    private Boolean status;

    private String createTime;

    private String lastUpdate;

    private String startTime;

    private String endTime;


    public AdPageBO() {
    }

    public AdPageBO(String id, String name, String url, String link, Integer sort, Boolean showName, Boolean status,
                    String createTime, String lastUpdate) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.link = link;
        this.sort = sort;
        this.showName = showName;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getShowName() {
        return showName;
    }
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    public void setShowName(Boolean showName) {
        this.showName = showName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExperienceRuleInsertBO{" +
                "id='" + id + '\'' +
                ",name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", link=" + link +
                ", sort='" + sort + '\'' +
                ", showName='" + showName + '\'' +
                ", status=" + status + '\'' +
                ", createTime=" + createTime + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
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
}