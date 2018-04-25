package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.UserHngs;

public class UserHngsRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<UserHngs> dataList;
    private UserHngs data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<UserHngs> getDataList() {
		return dataList;
	}
	public void setDataList(List<UserHngs> dataList) {
		this.dataList = dataList;
	}
	public UserHngs getData() {
		return data;
	}
	public void setData(UserHngs data) {
		this.data = data;
	}
    
    
}
