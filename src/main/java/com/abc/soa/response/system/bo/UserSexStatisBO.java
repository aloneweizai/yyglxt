package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserSexStatisBO implements Serializable {


    /**
     *性别，0：女，1：男
     */
    private String sexGroup;

    /**
     * 人数
     */
    private Integer count;

    public String getSexGroup() {
        return sexGroup;
    }

    public void setSexGroup(String sexGroup) {
        this.sexGroup = sexGroup;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
