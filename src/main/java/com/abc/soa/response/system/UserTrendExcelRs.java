package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;

import java.util.List;

public class UserTrendExcelRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserTrendExcelBO data;
	private List<UserTrendExcelBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserTrendExcelBO getData() {
		return data;
	}
	public void setData(UserTrendExcelBO data) {
		this.data = data;
	}


	public List<UserTrendExcelBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserTrendExcelBO> dataList) {
		this.dataList = dataList;
	}
}
