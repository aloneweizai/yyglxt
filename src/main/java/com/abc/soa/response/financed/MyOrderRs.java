package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Order;
import com.abc.soa.response.financed.bo.OrderExport;

import java.util.List;

public class MyOrderRs extends BaseResponse {
	private static final long serialVersionUID = 1L;

	private int total;

	private Order data;

	private List<OrderExport> orderList;

	private List<Order> dataList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public Order getData() {
		return data;
	}

	public void setData(Order data) {
		this.data = data;
	}

	public List<OrderExport> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderExport> orderList) {
		this.orderList = orderList;
	}

	public List<Order> getDataList() {
		return dataList;
	}

	public void setDataList(List<Order> dataList) {
		this.dataList = dataList;
	}
}
