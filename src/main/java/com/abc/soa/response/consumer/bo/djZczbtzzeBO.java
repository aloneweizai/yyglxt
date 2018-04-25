package com.abc.soa.response.consumer.bo;

import java.io.Serializable;

public class djZczbtzzeBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String zcbzDm;	//币种代码
	private String je;	//金额
	private String zcbzmc;	//币种名称

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getZcbzDm() {
		return zcbzDm;
	}

	public void setZcbzDm(String zcbzDm) {
		this.zcbzDm = zcbzDm;
	}

	public String getZcbzmc() {
		return zcbzmc;
	}

	public void setZcbzmc(String zcbzmc) {
		this.zcbzmc = zcbzmc;
	}
}
