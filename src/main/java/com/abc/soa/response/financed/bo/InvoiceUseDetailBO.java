package com.abc.soa.response.financed.bo;

import java.io.Serializable;


public class InvoiceUseDetailBO  implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * PK
	 */
	private String id;
	/****/
	private String useId;

	/**发票种类代码，字典表ID**/
	private String invoiceTypeCode;

	/**库存数量**/
	private Integer repoNum;

	/**申请数量**/
	private Integer applyNum;

	/**实发数量**/
	private Integer realNum;

	/**备注**/
	private String remark;

	/**可用份数**/
	private Integer usableShare;

	/**审批本数**/
	private Integer checkBook;

	/**发票分发流水号**/
	private String[] invoiceRepoIds;

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

	public Integer getRepoNum() {
		return repoNum;
	}

	public void setRepoNum(Integer repoNum) {
		this.repoNum = repoNum;
	}

	public Integer getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}

	public Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUsableShare() {
		return usableShare;
	}

	public void setUsableShare(Integer usableShare) {
		this.usableShare = usableShare;
	}

	public Integer getCheckBook() {
		return checkBook;
	}

	public void setCheckBook(Integer checkBook) {
		this.checkBook = checkBook;
	}

	public String[] getInvoiceRepoIds() {
		return invoiceRepoIds;
	}

	public void setInvoiceRepoIds(String[] invoiceRepoIds) {
		this.invoiceRepoIds = invoiceRepoIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
