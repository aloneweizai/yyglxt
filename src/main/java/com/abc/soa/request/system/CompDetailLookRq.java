package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

/**
 * API 查询条件
 * @author zhushuai 2017-10-18
 *
 */
public class CompDetailLookRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String timeInterval;
	private String doType;//1 用户标签分析统计 2 用户年龄分布统计 3 用户性别分布统计
	private String menu;
	private String startTime;//开始时间
	private String endTime;//结束时间
	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

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
}
