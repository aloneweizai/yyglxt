package com.abc.soa.request.consumer;

public class VipprivilegelevelReq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String level;
    private String privilege;
    private Boolean status;
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
    
}
