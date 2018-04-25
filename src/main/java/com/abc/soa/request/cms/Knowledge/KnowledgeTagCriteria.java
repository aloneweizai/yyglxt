package com.abc.soa.request.cms.Knowledge;

/**
 * @Author liuqi
 * @Date 2017/8/15 14:03
 * 知识库标签 查询参数
 */
public class KnowledgeTagCriteria {

    private Integer page;

    private Integer size;

    private String keywords;

    private String tagType;

    public Integer getPage() {
        return page;
    }

    public KnowledgeTagCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public KnowledgeTagCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public KnowledgeTagCriteria setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getTagType() {
        return tagType;
    }

    public KnowledgeTagCriteria setTagType(String tagType) {
        this.tagType = tagType;
        return this;
    }
}
