package com.abc.soa.request.bangbang;

/**
 * @Author liuQi
 * @Date 2017/10/11 17:20
 */
public class TopicRecommendCriteria {

    private Integer page;

    private Integer size;

    /*关键字查询*/
    private String keywords;

    /*是否推荐*/
    private Boolean isRecommend;

    /*回答数，评论数，点赞数*/
    private String sortFieldName;

    /*ASC.DESC*/
    private String sortName;

    private String type;

    public String getType() {
        return type;
    }

    public TopicRecommendCriteria setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public TopicRecommendCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public TopicRecommendCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public TopicRecommendCriteria setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public TopicRecommendCriteria setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
        return this;
    }

    public String getSortFieldName() {
        return sortFieldName;
    }

    public TopicRecommendCriteria setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
        return this;
    }

    public String getSortName() {
        return sortName;
    }

    public TopicRecommendCriteria setSortName(String sortName) {
        this.sortName = sortName;
        return this;
    }
}
