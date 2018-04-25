package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

public class WxModleMsglogRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String username;
    private String beginTime;
    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
