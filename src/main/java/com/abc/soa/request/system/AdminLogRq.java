package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

public class AdminLogRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 开始日期，用于查询
    private String startDate;
    // 结束日期，用于查询
    private String endDate;
    // 用户名，用于查询
    private String username;
    // 昵称，用于查询
    private String nickname;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
