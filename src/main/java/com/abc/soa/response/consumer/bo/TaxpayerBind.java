package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;

public class TaxpayerBind implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String username;
    private String nickname;
    private String nsrsbh;
    private String nsrmc;
    private String swjgmc;
    private Date createTime;
    private Boolean status;
    private Date expireDate;
    private String bindType;
    private Date lastUpdate;
    private String id;
    private String swjgdm;
	private String userId;
	/**
	 * 访问次数
	 */
	private Integer amount;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getSwjgmc() {
		return swjgmc;
	}
	public void setSwjgmc(String swjgmc) {
		this.swjgmc = swjgmc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getBindType() {
		return bindType;
	}
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getSwjgdm() {
		return swjgdm;
	}

	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
