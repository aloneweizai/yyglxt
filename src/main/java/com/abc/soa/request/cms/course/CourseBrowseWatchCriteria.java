package com.abc.soa.request.cms.course;

/**
 * @Author liuQi
 * @Date 2018/2/1 18:35
 */
public class CourseBrowseWatchCriteria {

    private Integer page;

    private Integer size;

    private String date;

    private String month;

    public Integer getPage() {
        return page;
    }

    public CourseBrowseWatchCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public CourseBrowseWatchCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getDate() {
        return date;
    }

    public CourseBrowseWatchCriteria setDate(String date) {
        this.date = date;
        return this;
    }

    public String getMonth() {
        return month;
    }

    public CourseBrowseWatchCriteria setMonth(String month) {
        this.month = month;
        return this;
    }
}
