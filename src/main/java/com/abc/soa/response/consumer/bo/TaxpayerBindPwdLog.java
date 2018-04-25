package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;

public class TaxpayerBindPwdLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String username;
    private String nickname;
    private String nsrsbh;
    private String frmc;
    private String frzjh;
    private Date createTime;
    private String ip;
    private String id;
	private String userId;
	private String message;
	private String code;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
