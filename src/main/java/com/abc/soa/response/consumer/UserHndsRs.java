package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.UserHnds;

public class UserHndsRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<UserHnds> dataList;
    private UserHnds data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<UserHnds> getDataList() {
		return dataList;
	}
	public void setDataList(List<UserHnds> dataList) {
		this.dataList = dataList;
	}
	public UserHnds getData() {
		return data;
	}
	public void setData(UserHnds data) {
		this.data = data;
	}
    
}
