package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

/**
 * 办税日历查询条件类
 * 
 * @author Administrator
 *
 */
public class TaxCalendarRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String startTime;
	private String endTime;
	private String sbyf;
	private String sbnf;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSbyf() {
		return sbyf;
	}

	public void setSbyf(String sbyf) {
		this.sbyf = sbyf;
	}

	public String getSbnf() {
		return sbnf;
	}

	public void setSbnf(String sbnf) {
		this.sbnf = sbnf;
	}

}
