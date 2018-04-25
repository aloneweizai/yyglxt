package com.abc.soa.request.consumer;


public class SubscriptionRq extends BaseRq{
	private static final long serialVersionUID = 1L;

	private String type;
	private String busiType;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
}
