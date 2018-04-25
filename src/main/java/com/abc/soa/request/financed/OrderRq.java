package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class OrderRq extends BaseRq {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单号
	private String orderNo;
	//private String name;
	//private String categoryId;
	//用户名
	private String username;
	//电话号码
	private String phone;
	//订单状态
	private String orderStatus;
	//private String payStatus;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;

	private String tradingChannels;

	private String tradeMethod;

	private String recommendUser;

	private String recommendPhone;

	private String goodsId;

	private String curriculumId;

	private String levelId;
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/*public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}*/

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/*public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
*/
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

	public String getTradingChannels() {
		return tradingChannels;
	}

	public void setTradingChannels(String tradingChannels) {
		this.tradingChannels = tradingChannels;
	}

	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public String getRecommendUser() {
		return recommendUser;
	}

	public void setRecommendUser(String recommendUser) {
		this.recommendUser = recommendUser;
	}

	public String getRecommendPhone() {
		return recommendPhone;
	}

	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
}
