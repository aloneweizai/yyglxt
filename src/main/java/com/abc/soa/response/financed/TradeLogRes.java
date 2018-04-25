package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.TradeLog;

public class TradeLogRes extends BaseResponse{
	private static final long serialVersionUID = -7859370887990688693L;
	private int total;

    private List<TradeLog> dataList;
    private TradeLog data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<TradeLog> getDataList() {
		return dataList;
	}
	public void setDataList(List<TradeLog> dataList) {
		this.dataList = dataList;
	}
	public TradeLog getData() {
		return data;
	}
	public void setData(TradeLog data) {
		this.data = data;
	}
    
    
}
