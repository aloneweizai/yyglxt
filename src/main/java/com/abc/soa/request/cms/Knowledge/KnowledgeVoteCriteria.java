package com.abc.soa.request.cms.Knowledge;

/**
 * @Author liuQi
 * @Date 2017/8/11 12:01
 * 投票管理 查询参数
 */
public class KnowledgeVoteCriteria {

    private Integer page;

    private Integer size;

    private String categoryCode;

    private String knowledgeType;

    private String keywords;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(String knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
