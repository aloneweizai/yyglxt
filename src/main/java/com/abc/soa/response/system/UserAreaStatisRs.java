package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserAreaStatisBO;

import java.util.List;

public class UserAreaStatisRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserAreaStatisBO data;
	private List<UserAreaStatisBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserAreaStatisBO getData() {
		return data;
	}
	public void setData(UserAreaStatisBO data) {
		this.data = data;
	}


	public List<UserAreaStatisBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserAreaStatisBO> dataList) {
		this.dataList = dataList;
	}
}
