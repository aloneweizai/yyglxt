package com.abc.soa.response.consumer.bo;

import java.io.Serializable;

public class ZzsncpbaBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//纳税人识别号
	private String nsrsbh;
	//纳税人名称
	private String nsrmc;
	//备案所属期起
	private String bassqq;
	//备案所属期止
	private String bassqz;
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
	//作废标志
	private String zfbz;

	public String getBassqq() {
		return bassqq;
	}

	public void setBassqq(String bassqq) {
		this.bassqq = bassqq;
	}

	public String getBassqz() {
		return bassqz;
	}

	public void setBassqz(String bassqz) {
		this.bassqz = bassqz;
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

	public String getZfbz() {
		return zfbz;
	}

	public void setZfbz(String zfbz) {
		this.zfbz = zfbz;
	}

}
