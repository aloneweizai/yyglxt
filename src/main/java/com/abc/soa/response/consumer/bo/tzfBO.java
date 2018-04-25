package com.abc.soa.response.consumer.bo;

import java.io.Serializable;

public class tzfBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tzfmc;	//投资方名称
	private String tzfjjxz;	//投资方经济性质
	private String tzbl;	//投资比例
	private String zjlxDm;	//证件类型代码
	private String gjDm;	//国籍代码
	private String zjhm;	//证件号码
	private String zcdz;	//注册地址
	private String zjlxmc;	//证件类型名称
	private String gjmc;	//国籍名称

	public String getGjDm() {
		return gjDm;
	}

	public void setGjDm(String gjDm) {
		this.gjDm = gjDm;
	}

	public String getGjmc() {
		return gjmc;
	}

	public void setGjmc(String gjmc) {
		this.gjmc = gjmc;
	}

	public String getTzbl() {
		return tzbl;
	}

	public void setTzbl(String tzbl) {
		this.tzbl = tzbl;
	}

	public String getTzfjjxz() {
		return tzfjjxz;
	}

	public void setTzfjjxz(String tzfjjxz) {
		this.tzfjjxz = tzfjjxz;
	}

	public String getTzfmc() {
		return tzfmc;
	}

	public void setTzfmc(String tzfmc) {
		this.tzfmc = tzfmc;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getZjlxDm() {
		return zjlxDm;
	}

	public void setZjlxDm(String zjlxDm) {
		this.zjlxDm = zjlxDm;
	}

	public String getZjlxmc() {
		return zjlxmc;
	}

	public void setZjlxmc(String zjlxmc) {
		this.zjlxmc = zjlxmc;
	}
}
