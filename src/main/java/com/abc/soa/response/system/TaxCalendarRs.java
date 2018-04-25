package com.abc.soa.response.system;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.TaxCalendar;

/**
 * 办税日历查询返回类
 * 
 * @author Administrator
 *
 */
public class TaxCalendarRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;

	private List<TaxCalendar> dataList;

	private TaxCalendar data;

	public List<TaxCalendar> getDataList() {
		return dataList;
	}

	public void setDataList(List<TaxCalendar> dataList) {
		this.dataList = dataList;
	}

	public TaxCalendar getData() {
		return data;
	}

	public void setData(TaxCalendar data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
