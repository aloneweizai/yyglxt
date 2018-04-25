package com.abc.soa.request.cms.feedback;

/**
 * @Author liuqi
 * @Date 2017/8/12 11:44
 */
public class FeedbackCriteria {

    private Integer page;

    private Integer size;

    private String feedbackType;

    private String sourceType;

    private Boolean isReply;


    public Integer getPage() {
        return page;
    }

    public FeedbackCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public FeedbackCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public FeedbackCriteria setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
        return this;
    }

    public String getSourceType() {
        return sourceType;
    }

    public FeedbackCriteria setSourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public Boolean getIsReply() {
        return isReply;
    }

    public FeedbackCriteria setIsReply(Boolean isReply) {
        this.isReply = isReply;
        return this;
    }
}
