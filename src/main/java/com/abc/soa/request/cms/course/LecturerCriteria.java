package com.abc.soa.request.cms.course;

/**
 * @Author liuQi
 * @Date 2017/8/16 09:59
 * 课程讲师 查询参数
 */
public class LecturerCriteria {

    private String lecturerName;

    private Integer page;

    private Integer size;

    public Integer getPage() {
        return page;
    }

    public LecturerCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public LecturerCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public LecturerCriteria setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
        return this;
    }
}
