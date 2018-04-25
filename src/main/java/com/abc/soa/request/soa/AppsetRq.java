package com.abc.soa.request.soa;

import com.abc.soa.request.consumer.BaseRq;

public class AppsetRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String appId;//应用ID
	private String name;//名称
	private String uri; //地址
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
