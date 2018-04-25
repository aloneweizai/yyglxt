package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.MessageQueryLog;

import java.util.List;

public class MessageQueryLogRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private MessageQueryLog data;
	private List<MessageQueryLog> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public MessageQueryLog getData() {
		return data;
	}
	public void setData(MessageQueryLog data) {
		this.data = data;
	}
	public List<MessageQueryLog> getDataList() {
		return dataList;
	}
	public void setDataList(List<MessageQueryLog> dataList) {
		this.dataList = dataList;
	}
	
	
}
