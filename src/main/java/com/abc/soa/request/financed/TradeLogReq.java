package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class TradeLogReq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String orderNo;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
} 
