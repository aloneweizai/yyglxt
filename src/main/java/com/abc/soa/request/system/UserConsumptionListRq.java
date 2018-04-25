package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

/**
 * API 查询条件
 * @author zhushuai 2017-10-18
 *
 */
public class UserConsumptionListRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startTime;//开始时间
	private String endTime;//结束时间
    private String tradeMethod;//交易方式（人名币：RMB，积分：POINTS)
	private String startDay;
	private String endDay;
	private String startCount;
	private String endCount;
	private String startPrice;
	private String endPrice;

	public String getEndCount() {
		return endCount;
	}

	public void setEndCount(String endCount) {
		this.endCount = endCount;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartCount() {
		return startCount;
	}

	public void setStartCount(String startCount) {
		this.startCount = startCount;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}
}
