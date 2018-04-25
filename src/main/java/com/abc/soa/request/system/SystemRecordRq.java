package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

public class SystemRecordRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //private String startTime;
    //private String endTime;
    private String location;
    private String appName;

    private String yyyyMMdd;

    private String feature;

    private String username;

   /* public String getStartTime() {
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
    }*/

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getYyyyMMdd() {
        return yyyyMMdd;
    }

    public void setYyyyMMdd(String yyyyMMdd) {
        this.yyyyMMdd = yyyyMMdd;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
