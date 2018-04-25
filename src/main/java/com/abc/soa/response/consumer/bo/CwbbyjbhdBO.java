package com.abc.soa.response.consumer.bo;

import java.io.Serializable;

public class CwbbyjbhdBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//纳税人识别号
	private String nsrsbh;
	//纳税人名称
	private String nsrmc;
	//报表报送期限代码
	private String bbbsqDm;
    //报表报送期限名称
	private String bbbsqmc;
	//有效期起
	private String yxqq;
	//有效期止
	private String yxqz;
	//有效标志
	private String yxbz;

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

	public String getYxqz() {
		return yxqz;
	}

	public void setYxqz(String yxqz) {
		this.yxqz = yxqz;
	}

	public String getYxqq() {
		return yxqq;
	}

	public void setYxqq(String yxqq) {
		this.yxqq = yxqq;
	}


	public String getBbbsqDm() {
		return bbbsqDm;
	}

	public void setBbbsqDm(String bbbsqDm) {
		this.bbbsqDm = bbbsqDm;
	}

	public String getBbbsqmc() {
		return bbbsqmc;
	}

	public void setBbbsqmc(String bbbsqmc) {
		this.bbbsqmc = bbbsqmc;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
}
