package com.abc.soa.response.financed.bo;

import com.abc.soa.response.consumer.bo.Consumer;

public class OrderBack {
	    private String id;
	    private String userId;
	    private String orderNo;
	    private String reason;
	    private String userRemark;
	    private String adminRemark;
	    private String expressNo;
	    private String expressComp;
	    private String status;
	    private java.util.Date createTime;
	    private java.util.Date lastUpdate;
	    /**
	     * 操作人员ID
	     **/
	    private String operatorUser;

	    private Consumer user;

	    public String getId() {
	        return this.id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getUserId() {
	        return this.userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }

	    public String getOrderNo() {
	        return this.orderNo;
	    }

	    public void setOrderNo(String orderNo) {
	        this.orderNo = orderNo;
	    }

	    public String getReason() {
	        return this.reason;
	    }

	    public void setReason(String reason) {
	        this.reason = reason;
	    }

	    public String getExpressNo() {
	        return this.expressNo;
	    }

	    public void setExpressNo(String expressNo) {
	        this.expressNo = expressNo;
	    }

	    public String getExpressComp() {
	        return this.expressComp;
	    }

	    public void setExpressComp(String expressComp) {
	        this.expressComp = expressComp;
	    }

	    public String getStatus() {
	        return this.status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public java.util.Date getCreateTime() {
	        return this.createTime;
	    }

	    public void setCreateTime(java.util.Date createTime) {
	        this.createTime = createTime;
	    }

	    public java.util.Date getLastUpdate() {
	        return this.lastUpdate;
	    }

	    public void setLastUpdate(java.util.Date lastUpdate) {
	        this.lastUpdate = lastUpdate;
	    }

	    public String getOperatorUser() {
	        return operatorUser;
	    }

	    public void setOperatorUser(String operatorUser) {
	        this.operatorUser = operatorUser;
	    }

	    public String getUserRemark() {
	        return userRemark;
	    }

	    public void setUserRemark(String userRemark) {
	        this.userRemark = userRemark;
	    }

	    public String getAdminRemark() {
	        return adminRemark;
	    }

	    public void setAdminRemark(String adminRemark) {
	        this.adminRemark = adminRemark;
	    }

	    public Consumer getUser() {
	        return user;
	    }

	    public void setUser(Consumer user) {
	        this.user = user;
	    }
}
