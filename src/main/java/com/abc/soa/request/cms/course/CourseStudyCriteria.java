package com.abc.soa.request.cms.course;


public class CourseStudyCriteria {

    private String nickname;

    private String curriculumId;

    private String begintime;

    private String endtime;

    private Integer page;

    private Integer size;

    private String username;
    public String getNickname() {
        return nickname;
    }

    public CourseStudyCriteria setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getCurriculumId() {
        return curriculumId;
    }

    public CourseStudyCriteria setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
        return this;
    }

    public String getBegintime() {
        return begintime;
    }

    public CourseStudyCriteria setBegintime(String begintime) {
        this.begintime = begintime;
        return this;
    }

    public String getEndtime() {
        return endtime;
    }

    public CourseStudyCriteria setEndtime(String endtime) {
        this.endtime = endtime;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public CourseStudyCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public CourseStudyCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
