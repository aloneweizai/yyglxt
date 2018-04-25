package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;


public class InvoiceDistributeBO implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**发票分发流水号**/
	private String id;

	/**发票编号(FK)**/
	private String invoiceRepoId;

	private String[] invoiceRepoIds;

	/**发票代码**/
	private String invoiceCode;

	/**发票号码起**/
	private String invoiceNoStart;

	/**发票号码止**/
	private String invoiceNoEnd;

	/****/
	private String status;

	/**发票本数**/
	private Integer share;

	/**发票本数**/
	private Integer book;

	/****/
	private String remark;

	/****/
	private String useId;

	/**发票种类代码，字典表ID**/
	private String invoiceTypeCode;

	/**分发人**/
	private String distributeUser;

	/**分发时间**/
	private Date distributeTime;

	/**签收人**/
	private String signUser;

	/**签收时间**/
	private Date signTime;

	private String fieldKey;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceRepoId() {
		return invoiceRepoId;
	}

	public void setInvoiceRepoId(String invoiceRepoId) {
		this.invoiceRepoId = invoiceRepoId;
	}

	public String[] getInvoiceRepoIds() {
		return invoiceRepoIds;
	}

	public void setInvoiceRepoIds(String[] invoiceRepoIds) {
		this.invoiceRepoIds = invoiceRepoIds;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUseId() {
		return useId;
	}

	public void setUseId(String useId) {
		this.useId = useId;
	}

	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}

	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}

	public String getDistributeUser() {
		return distributeUser;
	}

	public void setDistributeUser(String distributeUser) {
		this.distributeUser = distributeUser;
	}

	public Date getDistributeTime() {
		return distributeTime;
	}

	public void setDistributeTime(Date distributeTime) {
		this.distributeTime = distributeTime;
	}

	public String getSignUser() {
		return signUser;
	}

	public void setSignUser(String signUser) {
		this.signUser = signUser;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}
}
