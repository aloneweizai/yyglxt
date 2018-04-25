package com.abc.soa.request.consumer;

public class VipPrivilegeRq extends BaseRq {

	private static final long serialVersionUID = 7839870771757581980L;
	private String name;
	private String level;
	private Boolean status;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
