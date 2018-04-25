package com.abc.soa.request.vipGift;

import com.abc.soa.request.consumer.BaseRq;
/**
 * 会员礼物请求参数类
 * @author Administrator
 *
 */
public class VipGiftRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name; // 礼物名称
	private String category; // 会员等级
	private String status;// status
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
