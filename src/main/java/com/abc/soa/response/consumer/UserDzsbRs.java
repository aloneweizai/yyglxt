package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.UserDzsb;


public class UserDzsbRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<UserDzsb> dataList;
    private UserDzsb data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<UserDzsb> getDataList() {
		return dataList;
	}
	public void setDataList(List<UserDzsb> dataList) {
		this.dataList = dataList;
	}
	public UserDzsb getData() {
		return data;
	}
	public void setData(UserDzsb data) {
		this.data = data;
	}
    
    
}
