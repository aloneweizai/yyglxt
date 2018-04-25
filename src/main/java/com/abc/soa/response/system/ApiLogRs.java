package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.ApiLogBO;

public class ApiLogRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private ApiLogBO data;
	private String dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ApiLogBO getData() {
		return data;
	}
	public void setData(ApiLogBO data) {
		this.data = data;
	}
	public String getDataList() {
		return dataList;
	}
	public void setDataList(String dataList) {
		this.dataList = dataList;
	}
	
	
	
}
