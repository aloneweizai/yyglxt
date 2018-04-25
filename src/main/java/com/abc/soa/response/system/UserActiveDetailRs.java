package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserActiveDetailBO;

import java.util.List;

public class UserActiveDetailRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserActiveDetailBO data;
	private List<UserActiveDetailBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserActiveDetailBO getData() {
		return data;
	}
	public void setData(UserActiveDetailBO data) {
		this.data = data;
	}


	public List<UserActiveDetailBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserActiveDetailBO> dataList) {
		this.dataList = dataList;
	}
}
