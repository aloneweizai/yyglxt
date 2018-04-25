package com.abc.soa.response.system;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.MessageSendLog;

public class MessageSendLogRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private MessageSendLog data;
	private List<MessageSendLog> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public MessageSendLog getData() {
		return data;
	}
	public void setData(MessageSendLog data) {
		this.data = data;
	}
	public List<MessageSendLog> getDataList() {
		return dataList;
	}
	public void setDataList(List<MessageSendLog> dataList) {
		this.dataList = dataList;
	}
	
	
}
