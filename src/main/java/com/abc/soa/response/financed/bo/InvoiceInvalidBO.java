package com.abc.soa.response.financed.bo;

import java.io.Serializable;


/**
 * 发票信息
 **/
@SuppressWarnings("serial")
public class InvoiceInvalidBO implements Serializable {

	/**
	 * 发票申请ID
	 */
	private String id;
	/**
	 * 作废类型，0：发票订单，1：发票订单和发票
	 **/
	private Integer type;

	/**
	 * 发票性质：1.纸质发票 2.电子发票
	 **/
	private String property;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
