package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserRetainedRateListBO;

import java.util.List;

public class UserRetentionCountRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private UserRetainedRateListBO data;
	private List<UserRetainedRateListBO> dataList;

	public List<UserRetainedRateListBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UserRetainedRateListBO> dataList) {
		this.dataList = dataList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public UserRetainedRateListBO getData() {
		return data;
	}

	public void setData(UserRetainedRateListBO data) {
		this.data = data;
	}
}
