package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.SysTask;

public class SysTaskRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<SysTask> dataList;
    private SysTask data;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<SysTask> getDataList() {
		return dataList;
	}
	public void setDataList(List<SysTask> dataList) {
		this.dataList = dataList;
	}
	public SysTask getData() {
		return data;
	}
	public void setData(SysTask data) {
		this.data = data;
	}
    
    
}
