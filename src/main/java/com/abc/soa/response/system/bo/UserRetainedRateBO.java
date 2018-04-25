package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserRetainedRateBO extends TableBO implements Serializable {

    /**
     * 当月注册用户总数
     */
    private Integer userCount;

    /**
     * 留存用户总数
     */
    private Integer rateCount;

    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

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
}
