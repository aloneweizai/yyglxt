package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserSignBO extends TableBO implements Serializable {

    /**
     * 用户总数
     */
    private Integer userCount;

    /**
     * 流失用户总数
     */
    private Integer lossUserCount;

    /**
     * 流失率（流失用户总数/注册用户总数*100%）
     */
    private String rate;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getLossUserCount() {
        return lossUserCount;
    }

    public void setLossUserCount(Integer lossUserCount) {
        this.lossUserCount = lossUserCount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
