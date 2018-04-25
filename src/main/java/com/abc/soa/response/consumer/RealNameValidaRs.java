package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.ConsumerExtend;

public class RealNameValidaRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;

    private List<ConsumerExtend> dataList;
    private ConsumerExtend data;
	public ConsumerExtend getData() {
		return data;
	}
	public void setData(ConsumerExtend data) {
		this.data = data;
	}
	public List<ConsumerExtend> getDataList() {
		return dataList;
	}
	public void setDataList(List<ConsumerExtend> dataList) {
		this.dataList = dataList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	} 
}
