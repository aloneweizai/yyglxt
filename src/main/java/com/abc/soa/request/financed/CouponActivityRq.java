package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class CouponActivityRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -363936191767928505L;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 活动状态: 0-删除 1-草稿 2-启用 3-停用
	 */
	private String status;

	private String isOverdue;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}
}
