package com.abc.soa.response.bangbang;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2017/10/23 10:25
 */
public class SensitiveWords {

    /**
     * PK
     **/
    private String id;

    /**
     * 关键字
     **/
    private String keywords;

    /**
     * 是否有效
     **/
    private Boolean status;

    /**
     * 修改时间
     **/
    private java.util.Date updateTime;

    /**
     * 修改adminId
     **/
    private String updateAdmin;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Boolean getStatus() {
        return status;
    }

    public SensitiveWords setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public SensitiveWords setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdateAdmin() {
        return updateAdmin;
    }

    public SensitiveWords setUpdateAdmin(String updateAdmin) {
        this.updateAdmin = updateAdmin;
        return this;
    }
}
