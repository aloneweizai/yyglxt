package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.VipLog;
/**
 * 
 * @author zhushuai
 *
 */
public class VipLogRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<VipLog> dataList;
    private VipLog data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<VipLog> getDataList() {
		return dataList;
	}

	public void setDataList(List<VipLog> dataList) {
		this.dataList = dataList;
	}

	public VipLog getData() {
		return data;
	}

	public void setData(VipLog data) {
		this.data = data;
	}
    
}
