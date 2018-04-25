package com.abc.soa.response.consumer.bo;

import java.io.Serializable;

public class CwbbbaBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//纳税人识别号
	private String nsrsbh;
	//纳税人名称
	private String nsrmc;
	//会计制度准则代码
	private String kjzdzzDm;
	//会计制度准则名称
	private String kjzdzzmc;
	//有效期起
	private String yxqq;
	//有效期止
	private String yxqz;
	//修改日期
	private String xgrq;
	//修改人代码
	private String xgrDm;
	//修改人名称
	private String xgrmc;
	//录入人代码
	private String lrrDm;
	//录入日期
	private String lrrq;
	//录入人名称
	private String lrrmc;

	public String getKjzdzzDm() {
		return kjzdzzDm;
	}

	public void setKjzdzzDm(String kjzdzzDm) {
		this.kjzdzzDm = kjzdzzDm;
	}

	public String getKjzdzzmc() {
		return kjzdzzmc;
	}

	public void setKjzdzzmc(String kjzdzzmc) {
		this.kjzdzzmc = kjzdzzmc;
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

	public String getYxqz() {
		return yxqz;
	}

	public void setYxqz(String yxqz) {
		this.yxqz = yxqz;
	}

	public String getXgrq() {
		return xgrq;
	}

	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}

	public String getXgrDm() {
		return xgrDm;
	}

	public void setXgrDm(String xgrDm) {
		this.xgrDm = xgrDm;
	}

	public String getXgrmc() {
		return xgrmc;
	}

	public void setXgrmc(String xgrmc) {
		this.xgrmc = xgrmc;
	}

	public String getYxqq() {
		return yxqq;
	}

	public void setYxqq(String yxqq) {
		this.yxqq = yxqq;
	}

	public String getLrrDm() {
		return lrrDm;
	}

	public void setLrrDm(String lrrDm) {
		this.lrrDm = lrrDm;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getLrrmc() {
		return lrrmc;
	}

	public void setLrrmc(String lrrmc) {
		this.lrrmc = lrrmc;
	}
}
