package com.abc.soa.request.vipGift;

import com.abc.soa.request.consumer.BaseRq;

/**
 * 会员礼物请求类
 * 
 * @author Administrator
 *
 */
public class UgiftApplyRq extends BaseRq {
	private static final long serialVersionUID = 1L;

	private String name; // 礼物名称
	private String giftName; // 会员等级
	private String status;// status
	private String applyId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

}
