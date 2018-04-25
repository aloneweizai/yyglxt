package com.abc.soa.request.cms.course;

/**
 * @Author liuQi
 * @Date 2017/8/23 17:32
 */
public class CourseCriteria {

    private String title;//标题查询

    private String status;//状态

    private String classify;//分类

    private Integer page;

    private Integer size;

    private String recommend;

    private String isFree;
    /*回答数，评论数，点赞数*/
    private String sortFieldName;

    /*ASC.DESC*/
    private String sortName;

    private String vipLevel;

    public String getClassify() {
        return classify;
    }

    public CourseCriteria setClassify(String classify) {
        this.classify = classify;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseCriteria setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CourseCriteria setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public CourseCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public CourseCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getSortFieldName() {
        return sortFieldName;
    }

    public void setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }
}
