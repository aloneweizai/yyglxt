package com.abc.soa.request.cms.course;

/**
 * @Author liuQi
 * @Date 2017/9/29 16:49
 */
public class CourseOrderCriteria {

    private String goodsId;

    private String nickname;

    private String startTime;

    private String endTime;

    private Integer page;

    private Integer size;

    private String username;

    public String getGoodsId() {
        return goodsId;
    }

    public CourseOrderCriteria setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public CourseOrderCriteria setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public CourseOrderCriteria setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public CourseOrderCriteria setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public CourseOrderCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public CourseOrderCriteria setSize(Integer size) {
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
