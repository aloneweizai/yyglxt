package com.abc.soa.request.bangbang;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2017/10/17 17:35
 */
public class ContentAuditCriteria {

    private Integer page;

    private Integer size;

    /* 关键字查询 */
    private String content;

    /* 状态 */
    private String status;

    /* 开始时间 */
    private String beginTime;

    /* 结束时间 */
    private String endTime;

    /* 所属分类 */
    private String classifyCode;

    public Integer getPage() {
        return page;
    }

    public ContentAuditCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public ContentAuditCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ContentAuditCriteria setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public ContentAuditCriteria setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ContentAuditCriteria setContent(String content) {
        this.content = content;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public ContentAuditCriteria setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public ContentAuditCriteria setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }
}
