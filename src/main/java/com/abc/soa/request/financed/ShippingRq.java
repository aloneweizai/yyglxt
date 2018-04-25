package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class ShippingRq extends BaseRq{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//配送方式名称
	private String name;
	private String id;
	/**
	 * 启用状态：true|false
	 **/
	private Boolean status;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
} 
