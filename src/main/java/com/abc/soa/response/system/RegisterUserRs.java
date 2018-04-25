package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.RegisterUserBO;

import java.util.List;

public class RegisterUserRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private RegisterUserBO data;
	private List<RegisterUserBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public RegisterUserBO getData() {
		return data;
	}
	public void setData(RegisterUserBO data) {
		this.data = data;
	}


	public List<RegisterUserBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<RegisterUserBO> dataList) {
		this.dataList = dataList;
	}
}
