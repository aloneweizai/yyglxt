package com.abc.soa.response.financed.bo;

import java.util.Date;

import com.abc.common.soa.response.BaseResponse;

public class InvoiceRepoDetail extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
	//发票号码
    private String invoiceNo;
	//发票代码
    private String invoiceCode;
    private String invoiceName;
	//发票性质
    private String property;
	//状态
    private String status;
    private Date createTime;
    private Date lastUpdate;
	//备注
    private String remark;
	//发票编号
    private String invoiceRepoId;

	private String createUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInvoiceRepoId() {
		return invoiceRepoId;
	}
	public void setInvoiceRepoId(String invoiceRepoId) {
		this.invoiceRepoId = invoiceRepoId;
	}


	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}
