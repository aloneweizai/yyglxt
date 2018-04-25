package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.DbsxBO;
import com.abc.soa.response.system.bo.OrderCountBO;

public class OrderCountRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderCountBO dataList;

	public OrderCountBO getDataList() {
		return dataList;
	}

	public void setDataList(OrderCountBO dataList) {
		this.dataList = dataList;
	}
}
