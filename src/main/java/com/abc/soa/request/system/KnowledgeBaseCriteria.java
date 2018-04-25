package com.abc.soa.request.system;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class KnowledgeBaseCriteria {

    private String categoryCode; //分类code

    private String keywords;    //搜索关键字 subject

    private String seleKnowIds; // 已选择了的关联问题id  格式：id,id,id,

    private Boolean status; //状态

    private String type;//分类：QA知识，K问题

    private String recommend;//推荐

    private Boolean isOpen; //是否对外开放

    private Integer page;

    private Integer size;

    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public KnowledgeBaseCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public KnowledgeBaseCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSeleKnowIds() {
        return seleKnowIds;
    }

    public void setSeleKnowIds(String seleKnowIds) {
        this.seleKnowIds = seleKnowIds;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getType() {
        return type;
    }

    public KnowledgeBaseCriteria setType(String type) {
        this.type = type;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public KnowledgeBaseCriteria setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }
}
