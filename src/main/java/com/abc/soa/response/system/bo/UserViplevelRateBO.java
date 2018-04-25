package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserViplevelRateBO  implements Serializable {

    private String levelCode;
    private int all;
    private int increase;
    private String increasePercent;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public String getIncreasePercent() {
        return increasePercent;
    }

    public void setIncreasePercent(String increasePercent) {
        this.increasePercent = increasePercent;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }
}
