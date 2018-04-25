package com.abc.soa.response.consumer.bo;

import java.io.Serializable;

public class kkyhBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String kkyhmc;    //扣款银行名称
	private String kkyhzh;	//扣款银行账号

	public String getKkyhmc() {
		return kkyhmc;
	}

	public void setKkyhmc(String kkyhmc) {
		this.kkyhmc = kkyhmc;
	}

	public String getKkyhzh() {
		return kkyhzh;
	}

	public void setKkyhzh(String kkyhzh) {
		this.kkyhzh = kkyhzh;
	}
}
