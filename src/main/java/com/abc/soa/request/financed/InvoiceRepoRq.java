package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class InvoiceRepoRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private String invoiceNo;
	//发票代码
	private String invoiceCode;
	///**发票性质：1.纸质发票 2.电子发票**/
	private String property;
	//库存状态
	private String status;
	//
	private String invoiceRepoId;
	//发票种类
	private String invoiceTypeCode;
	//发票号码起
	private String invoiceNoStart;
	//发票号码止
	private String invoiceNoEnd;
	//发票编号起
	private String noStart;
	//发票编号止
	private String noEnd;
	//入库时间起
	private String startTime;
	//入库时间止
	private String endTime;
	//
	private String id;
	

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

	public String getInvoiceRepoId() {
		return invoiceRepoId;
	}

	public void setInvoiceRepoId(String invoiceRepoId) {
		this.invoiceRepoId = invoiceRepoId;
	}

	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}

	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}

	public String getInvoiceNoStart() {
		return invoiceNoStart;
	}

	public void setInvoiceNoStart(String invoiceNoStart) {
		this.invoiceNoStart = invoiceNoStart;
	}

	public String getInvoiceNoEnd() {
		return invoiceNoEnd;
	}

	public void setInvoiceNoEnd(String invoiceNoEnd) {
		this.invoiceNoEnd = invoiceNoEnd;
	}

	public String getNoStart() {
		return noStart;
	}

	public void setNoStart(String noStart) {
		this.noStart = noStart;
	}

	public String getNoEnd() {
		return noEnd;
	}

	public void setNoEnd(String noEnd) {
		this.noEnd = noEnd;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
