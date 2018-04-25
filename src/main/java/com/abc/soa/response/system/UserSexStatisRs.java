package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserSexStatisBO;

import java.util.List;

public class UserSexStatisRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserSexStatisBO data;
	private List<UserSexStatisBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserSexStatisBO getData() {
		return data;
	}
	public void setData(UserSexStatisBO data) {
		this.data = data;
	}


	public List<UserSexStatisBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserSexStatisBO> dataList) {
		this.dataList = dataList;
	}
}
