package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

/**
 * API 查询条件
 * @author zhushuai 2017-10-18
 *
 */
public class UserConsumptionRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startTime;//开始时间
	private String endTime;//结束时间
    private String tradeMethod;//交易方式（人名币：RMB，积分：POINTS)
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

	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}
}
