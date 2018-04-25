package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.OrderBack;

public class OrderbackRes extends BaseResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private OrderBack data;
	private List<OrderBack> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public OrderBack getData() {
		return data;
	}
	public void setData(OrderBack data) {
		this.data = data;
	}
	public List<OrderBack> getDataList() {
		return dataList;
	}
	public void setDataList(List<OrderBack> dataList) {
		this.dataList = dataList;
	}
	
	
}
