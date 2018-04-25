package com.abc.soa.request.consumer;

public class VipLogRq extends BaseRq{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
