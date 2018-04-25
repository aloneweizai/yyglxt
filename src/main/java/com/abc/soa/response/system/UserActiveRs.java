package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserActiveBO;

import java.util.List;

public class UserActiveRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserActiveBO dataList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public UserActiveBO getDataList() {
		return dataList;
	}

	public void setDataList(UserActiveBO dataList) {
		this.dataList = dataList;
	}
}
