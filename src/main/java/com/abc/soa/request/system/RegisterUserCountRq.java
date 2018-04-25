package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

/**
 * API 查询条件
 * @author zhushuai 2017-10-18
 *
 */
public class RegisterUserCountRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String timed;//时间段
	private String timeb;//对比时间
	private String days;
	private String timeb1;//对比时间
	public String getTimeb() {
		return timeb;
	}

	public void setTimeb(String timeb) {
		this.timeb = timeb;
	}

	public String getTimed() {
		return timed;
	}

	public void setTimed(String timed) {
		this.timed = timed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTimeb1() {
		return timeb1;
	}

	public void setTimeb1(String timeb1) {
		this.timeb1 = timeb1;
	}
}
