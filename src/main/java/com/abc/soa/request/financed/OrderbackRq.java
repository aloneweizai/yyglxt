package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class OrderbackRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String orderNo;
    private String username;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
