package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserTrendCountBO;

import java.util.List;

public class UserTrendCountRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserTrendCountBO data;
	private List<UserTrendCountBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserTrendCountBO getData() {
		return data;
	}
	public void setData(UserTrendCountBO data) {
		this.data = data;
	}


	public List<UserTrendCountBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserTrendCountBO> dataList) {
		this.dataList = dataList;
	}
}
