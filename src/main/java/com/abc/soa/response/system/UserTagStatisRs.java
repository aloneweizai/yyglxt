package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserTagStatisBO;

import java.util.List;

public class UserTagStatisRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserTagStatisBO data;
	private List<UserTagStatisBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserTagStatisBO getData() {
		return data;
	}
	public void setData(UserTagStatisBO data) {
		this.data = data;
	}


	public List<UserTagStatisBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserTagStatisBO> dataList) {
		this.dataList = dataList;
	}
}
