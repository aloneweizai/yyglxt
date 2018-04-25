package com.abc.soa.request.consumer;

import java.util.Date;

public class LotteryLogRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer isluck;

    private String userName;
    private String activityName;
    private String startTime;
    private String endTime;
    private  String state ;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getIsluck() {
        return isluck;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setIsluck(Integer isluck) {
        this.isluck = isluck;
    }
}
