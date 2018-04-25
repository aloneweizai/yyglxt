package com.abc.soa.response.system.bo;

import java.io.Serializable;

/**
 * 办税日历实体类
 * 
 * @author Administrator
 *
 */
public class TaxCalendar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String calId;// ID
	private String sbyf;// 申报月份
	private String description;// 描述
	private String sbrq;// 申报日期
	private String xgsj;// 修改时间

	public TaxCalendar() {

	}

	public TaxCalendar(String calId, String sbyf, String description, String sbrq, String xgsj) {
		this.calId = calId;
		this.sbyf = sbyf;
		this.description = description;
		this.sbrq = sbrq;
		this.xgsj = xgsj;
	}

	public String getCalId() {
		return calId;
	}

	public void setCalId(String calId) {
		this.calId = calId;
	}

	public String getSbyf() {
		return sbyf;
	}

	public void setSbyf(String sbyf) {
		this.sbyf = sbyf;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSbrq() {
		return sbrq;
	}

	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

}
