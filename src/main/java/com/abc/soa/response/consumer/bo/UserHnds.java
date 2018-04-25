package com.abc.soa.response.consumer.bo;

import java.util.Date;

import com.abc.common.soa.response.BaseResponse;

public class UserHnds extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String userId;
    private String username;
    private String subuser;
    private String nsrmc;
    private String nsrsbh;
    private String djxh;
    private String shxydm;
    private String swjgMc;
    private String swjgDm;
    private boolean status;
    private Date createTime;
    private Date lastUpdate;
   
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubuser() {
		return subuser;
	}
	public void setSubuser(String subuser) {
		this.subuser = subuser;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getDjxh() {
		return djxh;
	}
	public void setDjxh(String djxh) {
		this.djxh = djxh;
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
