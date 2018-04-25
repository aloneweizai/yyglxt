package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

public class SubscriptionRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<Subscription> dataList;
    private Subscription data;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}


	public List<Subscription> getDataList() {
		return dataList;
	}

	public void setDataList(List<Subscription> dataList) {
		this.dataList = dataList;
	}

	public Subscription getData() {
		return data;
	}

	public void setData(Subscription data) {
		this.data = data;
	}
}
