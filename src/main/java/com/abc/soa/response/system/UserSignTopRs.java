package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserSignTopBO;

import java.util.List;

public class UserSignTopRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserSignTopBO data;
	private List<UserSignTopBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserSignTopBO getData() {
		return data;
	}
	public void setData(UserSignTopBO data) {
		this.data = data;
	}


	public List<UserSignTopBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserSignTopBO> dataList) {
		this.dataList = dataList;
	}
}
