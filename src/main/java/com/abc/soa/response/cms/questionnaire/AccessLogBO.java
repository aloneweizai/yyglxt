package com.abc.soa.response.cms.questionnaire;

/**
 * @Author liuqi
 * @Date 2017/7/6 14:24
 */
public class AccessLogBO {

    private String id;
    private String questionId;
    private String userId;
    private String accessTerminal;
    private String ipAddress;
    private java.util.Date createTime;
    private String days;
    private String count;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setAccessTerminal(String accessTerminal) {
        this.accessTerminal = accessTerminal;
    }

    public String getAccessTerminal() {
        return this.accessTerminal;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
