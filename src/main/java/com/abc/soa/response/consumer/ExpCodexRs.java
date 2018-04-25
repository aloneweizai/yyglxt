package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.ExpCodex;

public class ExpCodexRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<ExpCodex> dataList;
    private ExpCodex data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<ExpCodex> getDataList() {
		return dataList;
	}
	public void setDataList(List<ExpCodex> dataList) {
		this.dataList = dataList;
	}
	public ExpCodex getData() {
		return data;
	}
	public void setData(ExpCodex data) {
		this.data = data;
	}
    
    
}  
