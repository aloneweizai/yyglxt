package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;

public class MyOrderMoneyRs extends BaseResponse {
	private static final long serialVersionUID = 1L;

	private int total;

	private Double data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}
}
