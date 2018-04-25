package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.DbsxBO;

public class DbsxRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DbsxBO dataList;

	public DbsxBO getDataList() {
		return dataList;
	}

	public void setDataList(DbsxBO dataList) {
		this.dataList = dataList;
	}
}
