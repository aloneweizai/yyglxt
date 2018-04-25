package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserActiveDetailBO implements Serializable {

    private String date;
    /**
     * 条数
     */
    private Integer register;

    private Integer liveUsers;
    /**
     * 时间
     */
    private String liveUserPercent;

    private Integer allRegister;

    private String dateStr;

    public Integer getAllRegister() {
        return allRegister;
    }

    public void setAllRegister(Integer allRegister) {
        this.allRegister = allRegister;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLiveUserPercent() {
        return liveUserPercent;
    }

    public void setLiveUserPercent(String liveUserPercent) {
        this.liveUserPercent = liveUserPercent;
    }

    public Integer getLiveUsers() {
        return liveUsers;
    }

    public void setLiveUsers(Integer liveUsers) {
        this.liveUsers = liveUsers;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
