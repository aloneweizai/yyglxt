package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class LogisticsRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String compName;
    
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
}
