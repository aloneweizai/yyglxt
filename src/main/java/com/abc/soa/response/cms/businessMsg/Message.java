package com.abc.soa.response.cms.businessMsg;

import java.util.Date;

/**
 * @Author liuqi
 * @Date 2017/8/14 16:43
 */
public class Message {

    private String id;

    private String userId;

    private String businessId;

    //消息业务类型
    private String busiType;

    private String content;

    private String status;

    private Date createTime;

    private Date lastUpdate;

    private String type;

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusiType() {
        return busiType;
    }

    public Message setBusiType(String busiType) {
        this.busiType = busiType;
        return this;
    }
}
