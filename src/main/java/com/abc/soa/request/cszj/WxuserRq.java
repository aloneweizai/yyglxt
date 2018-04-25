package com.abc.soa.request.cszj;

import com.abc.soa.request.consumer.BaseRq;

import java.util.Date;

public class WxuserRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //查询条件
    private String openid;//用户的标识，对当前公众号唯一
    private String nickname;//昵称
    //private Date subscribe_time;//关注时间
    private String startTime;
    private String endTime;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
