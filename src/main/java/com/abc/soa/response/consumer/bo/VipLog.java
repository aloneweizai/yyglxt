package com.abc.soa.response.consumer.bo;

import java.util.Date;

import com.abc.common.soa.response.BaseResponse;

public class VipLog extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String source;
    private String levelId;
    private String level;
    private Date createTime;
    private Date vipExpireDate;
	//是否过期
	private Boolean outdated;
	private Boolean isChecked;
	private String orderStatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getVipExpireDate() {
		return vipExpireDate;
	}

	public void setVipExpireDate(Date vipExpireDate) {
		this.vipExpireDate = vipExpireDate;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Boolean getOutdated() {
		return outdated;
	}

	public void setOutdated(Boolean outdated) {
		this.outdated = outdated;
	}
}
