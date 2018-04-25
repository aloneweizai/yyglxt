package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class InvoiceBackRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String expressNo;
    private String userOrderNo;
    private String invoiceNo;
    private String sendExpressNo;
    
    
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getUserOrderNo() {
		return userOrderNo;
	}
	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getSendExpressNo() {
		return sendExpressNo;
	}
	public void setSendExpressNo(String sendExpressNo) {
		this.sendExpressNo = sendExpressNo;
	}
    
    
}
