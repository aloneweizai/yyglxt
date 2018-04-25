package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.CompRegisterCountBO;

import java.util.List;

public class CompRegisterCountRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private CompRegisterCountBO data;
	private List<CompRegisterCountBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public CompRegisterCountBO getData() {
		return data;
	}
	public void setData(CompRegisterCountBO data) {
		this.data = data;
	}


	public List<CompRegisterCountBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<CompRegisterCountBO> dataList) {
		this.dataList = dataList;
	}
}
