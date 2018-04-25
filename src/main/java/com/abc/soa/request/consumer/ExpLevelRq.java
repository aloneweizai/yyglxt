package com.abc.soa.request.consumer;


public class ExpLevelRq extends BaseRq{
	private static final long serialVersionUID = 1L;

	private String name;
	private Boolean status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
    
    
}
