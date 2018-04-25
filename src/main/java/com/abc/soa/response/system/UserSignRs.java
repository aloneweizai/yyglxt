package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserSignBO;

import java.util.List;

public class UserSignRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserSignBO data;
	private List<UserSignBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserSignBO getData() {
		return data;
	}
	public void setData(UserSignBO data) {
		this.data = data;
	}


	public List<UserSignBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserSignBO> dataList) {
		this.dataList = dataList;
	}
}
