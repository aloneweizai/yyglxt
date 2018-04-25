package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserRetentionBO extends TableBO implements Serializable {

    /**
     * 当月注册用户总数
     */
    private Integer userCount;

    /**
     * 留存用户总数
     */
    private Integer rateCount;

    private String startTime;

    private String endTime;

    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private Double rate1;

    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private Double rate2;
    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private Double rate3;
    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private Double rate4;
    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private Double rate5;
    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private Double rate6;

    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private Double rate7;

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
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

    public Double getRate1() {
        return rate1;
    }

    public void setRate1(Double rate1) {
        this.rate1 = rate1;
    }

    public Double getRate2() {
        return rate2;
    }

    public void setRate2(Double rate2) {
        this.rate2 = rate2;
    }

    public Double getRate3() {
        return rate3;
    }

    public void setRate3(Double rate3) {
        this.rate3 = rate3;
    }

    public Double getRate4() {
        return rate4;
    }

    public void setRate4(Double rate4) {
        this.rate4 = rate4;
    }

    public Double getRate5() {
        return rate5;
    }

    public void setRate5(Double rate5) {
        this.rate5 = rate5;
    }

    public Double getRate6() {
        return rate6;
    }

    public void setRate6(Double rate6) {
        this.rate6 = rate6;
    }

    public Double getRate7() {
        return rate7;
    }

    public void setRate7(Double rate7) {
        this.rate7 = rate7;
    }
}
