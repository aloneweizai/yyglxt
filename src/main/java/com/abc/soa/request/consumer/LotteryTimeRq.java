package com.abc.soa.request.consumer;

public class LotteryTimeRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
