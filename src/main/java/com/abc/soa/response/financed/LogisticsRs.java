package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Logistics;

public class LogisticsRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<Logistics> dataList;
    private Logistics data;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Logistics> getDataList() {
		return dataList;
	}
	public void setDataList(List<Logistics> dataList) {
		this.dataList = dataList;
	}
	public Logistics getData() {
		return data;
	}
	public void setData(Logistics data) {
		this.data = data;
	}
    
    
}
