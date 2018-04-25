package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class CompBingStatisBO implements Serializable {


    /**
     *年龄段
     */
    private String type;

    /**
     * 人数
     */
    private Integer bindCount;

    private String timeInterval;

    private String month;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBindCount() {
        return bindCount;
    }

    public void setBindCount(Integer bindCount) {
        this.bindCount = bindCount;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
