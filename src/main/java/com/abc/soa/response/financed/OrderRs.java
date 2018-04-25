package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Order;
import com.abc.soa.response.financed.bo.OrderExport;

import java.util.List;

public class OrderRs extends BaseResponse {
	private static final long serialVersionUID = 1L;

	private int total;

	private String dataList;
	private Order data;

	private List<OrderExport> orderList;

	private List<Order> dataList1;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDataList() {
		return dataList;
	}

	public void setDataList(String dataList) {
		this.dataList = dataList;
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

	public List<Order> getDataList1() {
		return dataList1;
	}

	public void setDataList1(List<Order> dataList1) {
		this.dataList1 = dataList1;
	}
}
