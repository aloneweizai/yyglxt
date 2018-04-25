package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserRFMBO;

import java.util.List;

public class UserConsumptionRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserRFMBO data;
	private List<UserRFMBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserRFMBO getData() {
		return data;
	}
	public void setData(UserRFMBO data) {
		this.data = data;
	}


	public List<UserRFMBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserRFMBO> dataList) {
		this.dataList = dataList;
	}
}
