package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

/**
 * API 查询条件
 * @author zhushuai 2017-10-18
 *
 */
public class UserActiveCountRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startDay;//开始时间
	private String endDay;//结束时间
	private String startMon;//开始时间
	private String endMon;//结束时间
	private String startYear;//开始时间
	private String endYear;//结束时间
    private String type;

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getEndMon() {
		return endMon;
	}

	public void setEndMon(String endMon) {
		this.endMon = endMon;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartMon() {
		return startMon;
	}

	public void setStartMon(String startMon) {
		this.startMon = startMon;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
