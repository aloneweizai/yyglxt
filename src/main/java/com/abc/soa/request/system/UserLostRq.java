package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

/**
 * API 查询条件
 * @author zhushuai 2017-10-18
 *
 */
public class UserLostRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String yearTime; //时间（年度）
	private String months;//流失间隔周期（1个月、2个月、3个月…12个月）

	public String getYearTime() {
		return yearTime;
	}

	public void setYearTime(String yearTime) {
		this.yearTime = yearTime;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}
}
