package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.RedisGlBO;

import java.util.List;
import java.util.Set;

public class RedisGlRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private Set data;
	private List<RedisGlBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<RedisGlBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<RedisGlBO> dataList) {
		this.dataList = dataList;
	}


	public Set getData() {
		return data;
	}

	public void setData(Set data) {
		this.data = data;
	}
}
