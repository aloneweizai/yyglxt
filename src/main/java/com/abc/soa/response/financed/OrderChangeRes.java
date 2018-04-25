package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.OrderExchange;


public class OrderChangeRes extends BaseResponse{
	private static final long serialVersionUID = 1L;
	private int total;
	private OrderExchange data;
	private List<OrderExchange> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public OrderExchange getData() {
		return data;
	}
	public void setData(OrderExchange data) {
		this.data = data;
	}
	public List<OrderExchange> getDataList() {
		return dataList;
	}
	public void setDataList(List<OrderExchange> dataList) {
		this.dataList = dataList;
	}
	
	
}
