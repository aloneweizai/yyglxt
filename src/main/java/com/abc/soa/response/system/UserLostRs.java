package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserLostBO;

import java.util.List;

public class UserLostRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserLostBO data;
	private List<UserLostBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserLostBO getData() {
		return data;
	}
	public void setData(UserLostBO data) {
		this.data = data;
	}


	public List<UserLostBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserLostBO> dataList) {
		this.dataList = dataList;
	}
}
