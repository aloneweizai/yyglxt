package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.CompUsingStatisBO;

import java.util.List;

public class CompUsingStatisRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private CompUsingStatisBO data;
	private List<CompUsingStatisBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public CompUsingStatisBO getData() {
		return data;
	}
	public void setData(CompUsingStatisBO data) {
		this.data = data;
	}


	public List<CompUsingStatisBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<CompUsingStatisBO> dataList) {
		this.dataList = dataList;
	}
}
