package com.abc.soa.response.cszj.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by relic5 on 2017/6/19.
 */
public class WxRedEnvelopUpdateBO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 口令ID
     */
    private String id;

    /**
     * 创建时间
     */
    private String createTimes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 微信OpenID
     */
    private String openId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(String createTimes) {
        this.createTimes = createTimes;
    }
}
