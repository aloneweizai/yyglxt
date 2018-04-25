package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class CouponUserRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -363936191767928505L;
	private String orderNo;

	private String activityId;

	private String categoryIds;
//0:未领取 1:已领取 2:已使用 3:已冻结 4:已删除 5:已过期 6:已作废
	private String status;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
