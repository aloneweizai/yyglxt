package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.ExpLevel;

public class ExpLevelRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<ExpLevel> dataList;
    private ExpLevel data;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<ExpLevel> getDataList() {
		return dataList;
	}
	public void setDataList(List<ExpLevel> dataList) {
		this.dataList = dataList;
	}
	public ExpLevel getData() {
		return data;
	}
	public void setData(ExpLevel data) {
		this.data = data;
	}
    
    
}
