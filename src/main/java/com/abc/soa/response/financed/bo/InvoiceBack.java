package com.abc.soa.response.financed.bo;

import java.io.Serializable;


public class InvoiceBack implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String userId;
    private String expressId;
    private String reason;
    private String remark;
    private String userRemark;
    private String adminRemark;
	private String expressNo;
    private String expressComp;
    private String status;
    private java.util.Date createTime;
    private java.util.Date lastUpdate;

    /**操作人员ID**/
    private String operatorUser;

    private String sendExpressNo;
    private String invoiceId;
    private Invoice invoiceBO;
    
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
	public String getExpressId() {
		return expressId;
	}
	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressComp() {
		return expressComp;
	}
	public void setExpressComp(String expressComp) {
		this.expressComp = expressComp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getLastUpdate() {
		return lastUpdate;
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
	public String getSendExpressNo() {
		return sendExpressNo;
	}
	public void setSendExpressNo(String sendExpressNo) {
		this.sendExpressNo = sendExpressNo;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Invoice getInvoiceBO() {
		return invoiceBO;
	}
	public void setInvoiceBO(Invoice invoiceBO) {
		this.invoiceBO = invoiceBO;
	}
    
    
    
}
