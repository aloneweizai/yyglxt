package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserViplevelRateBO;

import java.util.List;

public class UserViplevelRateRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserViplevelRateBO data;
	private List<UserViplevelRateBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserViplevelRateBO getData() {
		return data;
	}
	public void setData(UserViplevelRateBO data) {
		this.data = data;
	}


	public List<UserViplevelRateBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserViplevelRateBO> dataList) {
		this.dataList = dataList;
	}
}
