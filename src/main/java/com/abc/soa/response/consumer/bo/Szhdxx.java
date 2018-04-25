package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Szhdxx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//征收率
	private String zsl;
	//核定期限起
	private String hdqxq;
	//核定期限止
	private String hdqxz;
	//征收项目代码
	private String zsxmDm;
	//征收项目名称
	private String zsxmMc;
	//征收品目代码
	private String zspmDm;
	//征收品目名称
	private String zspmMc;
	//税率
	private String sl;

	public String getZsl() {
		return zsl;
	}

	public void setZsl(String zsl) {
		this.zsl = zsl;
	}

	public String getZsxmDm() {
		return zsxmDm;
	}

	public void setZsxmDm(String zsxmDm) {
		this.zsxmDm = zsxmDm;
	}

	public String getZsxmMc() {
		return zsxmMc;
	}

	public void setZsxmMc(String zsxmMc) {
		this.zsxmMc = zsxmMc;
	}

	public String getZspmDm() {
		return zspmDm;
	}

	public void setZspmDm(String zspmDm) {
		this.zspmDm = zspmDm;
	}

	public String getZspmMc() {
		return zspmMc;
	}

	public void setZspmMc(String zspmMc) {
		this.zspmMc = zspmMc;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getHdqxq() {
		return hdqxq;
	}

	public void setHdqxq(String hdqxq) {
		this.hdqxq = hdqxq;
	}

	public String getHdqxz() {
		return hdqxz;
	}

	public void setHdqxz(String hdqxz) {
		this.hdqxz = hdqxz;
	}
}
