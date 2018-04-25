package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserBehaviorStatisBO;

import java.util.List;

public class UserBehaviorStatisRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserBehaviorStatisBO data;
	private List<UserBehaviorStatisBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserBehaviorStatisBO getData() {
		return data;
	}
	public void setData(UserBehaviorStatisBO data) {
		this.data = data;
	}


	public List<UserBehaviorStatisBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserBehaviorStatisBO> dataList) {
		this.dataList = dataList;
	}
}
