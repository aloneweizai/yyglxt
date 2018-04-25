package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserExplevelRateBO;

import java.util.List;

public class UserExplevelRateRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserExplevelRateBO data;
	private List<UserExplevelRateBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserExplevelRateBO getData() {
		return data;
	}
	public void setData(UserExplevelRateBO data) {
		this.data = data;
	}


	public List<UserExplevelRateBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserExplevelRateBO> dataList) {
		this.dataList = dataList;
	}
}
