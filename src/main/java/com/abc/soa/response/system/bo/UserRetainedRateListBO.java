package com.abc.soa.response.system.bo;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserRetainedRateListBO extends TableBO implements Serializable {

    /**
     * 日期
     */
    private Date date;

    private List<UserRetainedRateBO> userRetainedRateBOList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<UserRetainedRateBO> getUserRetainedRateBOList() {
        return userRetainedRateBOList;
    }

    public void setUserRetainedRateBOList(List<UserRetainedRateBO> userRetainedRateBOList) {
        this.userRetainedRateBOList = userRetainedRateBOList;
    }
}
