package com.abc.soa.request.consumer;

public class LotteryActivityipRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String activityId;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
