package com.abc.soa.response.financed.bo;

import java.io.Serializable;


/**
 * 发票信息
 **/
@SuppressWarnings("serial")
public class InvoiceCheck implements Serializable {

	private String id;
	/**
	 * 是否同意开票
	 **/
	private Boolean isBilling;

	/**发票详情ID**/
	private String detailId;

	private String remark;
	/**
	 * 发票类型代码
	 */
	private String type;

	public Boolean getIsBilling() {
		return isBilling;
	}

	public void setIsBilling(Boolean isBilling) {
		this.isBilling = isBilling;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
