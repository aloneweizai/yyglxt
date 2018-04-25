package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserAreaStatisBO implements Serializable {


    /**
     * 区域名称
     */
    private String regionName;
    /**
     * 区域代码
     */
    private String regionCode;
    /**
     * 数量
     */
    private Integer allCount;

    /**
     * 时间区间
     */
    private String timeInterval;

    /**
     * 标签名
     */
    private String tagName;

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
