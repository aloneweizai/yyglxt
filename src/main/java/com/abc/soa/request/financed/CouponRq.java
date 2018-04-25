package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class CouponRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -363936191767928505L;
	/**
	 * 优惠劵模式：固定-FIXED, 浮动-FLOAT
	 */
	private String couponMode;

	/**
	 * 优惠类型：满减-MANJIAN，立减-LIJIAN，折扣-ZHEKOU
	 */
	private String couponType;
	/**
	 * 状态: 0-删除 1-草稿 2-启用 3-停用
	 */
	private String status;

	public String getCouponMode() {
		return couponMode;
	}

	public void setCouponMode(String couponMode) {
		this.couponMode = couponMode;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
