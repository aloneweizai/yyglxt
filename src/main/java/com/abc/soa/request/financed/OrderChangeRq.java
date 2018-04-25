package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class OrderChangeRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单号
	private String orderNo;
	//用户名
    private String username;
	//申请类型
    private String type;
	//订单状态
    private String status;
    
    
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
