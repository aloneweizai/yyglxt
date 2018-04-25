package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserServiceCompBO;

import java.util.List;

public class UserServiceCompRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserServiceCompBO data;
	private List<UserServiceCompBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserServiceCompBO getData() {
		return data;
	}
	public void setData(UserServiceCompBO data) {
		this.data = data;
	}


	public List<UserServiceCompBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserServiceCompBO> dataList) {
		this.dataList = dataList;
	}
}
