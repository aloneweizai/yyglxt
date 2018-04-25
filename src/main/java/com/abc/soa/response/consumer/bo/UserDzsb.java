package com.abc.soa.response.consumer.bo;

import java.util.Date;

import com.abc.common.soa.response.BaseResponse;

public class UserDzsb extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String userId;
	private String djxh;
	private String nsrsbh;
	private String nsrmc;
	private String shxydm;
	private String swjgMc;
	private String swjgDm;
	private boolean status;
	private Date createTime;
	private Date lastUpdate;

	private Date expireTime;
	// 软件延期到期日
	private Date expandExpireTime;
	// 法人名称
	private String frmc;
	// 法人证件号
	private String frzjh;
	// 最后登录时间
	private Date lastLoginTime;
	// 纳税人类型，纳税人类型 0未核定，1增值税小规模 2：增值税一般 :3：纯所得税
	private String nsrlx;
	// 是否个体建账户 Y/N
	private String sfgtjzh;
	// 税务登记日期
	private String djrq;

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getExpandExpireTime() {
		return expandExpireTime;
	}

	public void setExpandExpireTime(Date expandExpireTime) {
		this.expandExpireTime = expandExpireTime;
	}

	public String getFrmc() {
		return frmc;
	}

	public void setFrmc(String frmc) {
		this.frmc = frmc;
	}

	public String getFrzjh() {
		return frzjh;
	}

	public void setFrzjh(String frzjh) {
		this.frzjh = frzjh;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getNsrlx() {
		return nsrlx;
	}

	public void setNsrlx(String nsrlx) {
		this.nsrlx = nsrlx;
	}

	public String getSfgtjzh() {
		return sfgtjzh;
	}

	public void setSfgtjzh(String sfgtjzh) {
		this.sfgtjzh = sfgtjzh;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

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

	public String getDjxh() {
		return djxh;
	}

	public void setDjxh(String djxh) {
		this.djxh = djxh;
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

	public String getShxydm() {
		return shxydm;
	}

	public void setShxydm(String shxydm) {
		this.shxydm = shxydm;
	}

	public String getSwjgMc() {
		return swjgMc;
	}

	public void setSwjgMc(String swjgMc) {
		this.swjgMc = swjgMc;
	}

	public String getSwjgDm() {
		return swjgDm;
	}

	public void setSwjgDm(String swjgDm) {
		this.swjgDm = swjgDm;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
