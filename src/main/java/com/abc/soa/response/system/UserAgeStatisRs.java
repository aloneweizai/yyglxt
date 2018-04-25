package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserAgeStatisBO;

import java.util.List;

public class UserAgeStatisRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserAgeStatisBO data;
	private List<UserAgeStatisBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserAgeStatisBO getData() {
		return data;
	}
	public void setData(UserAgeStatisBO data) {
		this.data = data;
	}


	public List<UserAgeStatisBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserAgeStatisBO> dataList) {
		this.dataList = dataList;
	}
}
