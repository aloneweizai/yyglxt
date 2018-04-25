package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserActiveBO implements Serializable {

    /**
     * 昨日活跃用户数
     */
    private float yesterday;
    /**
     * 过去7日活跃用户数
     */
    private float lastweek;
    /**
     * 昨日活跃用户数除以过去7日活跃用户数
     */
    private String lastweekDevidedbyLastweek;
    /**
     * 过去30日活跃用户数
     */
    private float last30Days;
    /**
     * 昨日活跃用户数除以过去30日活跃用户数
     */
    private String last30DaysDevidedbyYesterday;

    private float thismonth;

    public float getLast30Days() {
        return last30Days;
    }

    public void setLast30Days(float last30Days) {
        this.last30Days = last30Days;
    }

    public float getLastweek() {
        return lastweek;
    }

    public void setLastweek(float lastweek) {
        this.lastweek = lastweek;
    }

    public String getLastweekDevidedbyLastweek() {
        return lastweekDevidedbyLastweek;
    }

    public void setLastweekDevidedbyLastweek(String lastweekDevidedbyLastweek) {
        this.lastweekDevidedbyLastweek = lastweekDevidedbyLastweek;
    }

    public float getYesterday() {
        return yesterday;
    }

    public void setYesterday(float yesterday) {
        this.yesterday = yesterday;
    }

    public String getLast30DaysDevidedbyYesterday() {
        return last30DaysDevidedbyYesterday;
    }

    public void setLast30DaysDevidedbyYesterday(String last30DaysDevidedbyYesterday) {
        this.last30DaysDevidedbyYesterday = last30DaysDevidedbyYesterday;
    }

    public float getThismonth() {
        return thismonth;
    }

    public void setThismonth(float thismonth) {
        this.thismonth = thismonth;
    }
}
