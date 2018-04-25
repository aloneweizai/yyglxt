package com.abc.soa.response.consumer.bo;

import java.io.Serializable;

public class JzjtzgBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//纳税人识别号
	private String nsrsbh;
	//纳税人名称
	private String nsrmc;
	//有效标志
	private String yxbz;
	//数据截止日期
	private String sjjzrq;
	//减免期限起
	private String jmqxq;
	//减免期限止
	private String jmqxz;
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
	//作废人代码
	private String zfrDm;
	//作废人名称
	private String zfrmc;

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

	public String getZfrDm() {
		return zfrDm;
	}

	public void setZfrDm(String zfrDm) {
		this.zfrDm = zfrDm;
	}

	public String getZfrmc() {
		return zfrmc;
	}

	public void setZfrmc(String zfrmc) {
		this.zfrmc = zfrmc;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getSjjzrq() {
		return sjjzrq;
	}

	public void setSjjzrq(String sjjzrq) {
		this.sjjzrq = sjjzrq;
	}

	public String getJmqxq() {
		return jmqxq;
	}

	public void setJmqxq(String jmqxq) {
		this.jmqxq = jmqxq;
	}

	public String getJmqxz() {
		return jmqxz;
	}

	public void setJmqxz(String jmqxz) {
		this.jmqxz = jmqxz;
	}
}
