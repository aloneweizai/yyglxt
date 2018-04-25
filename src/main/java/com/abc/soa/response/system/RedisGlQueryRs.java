package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.RedisGlBO;

import java.util.List;
import java.util.Set;

public class RedisGlQueryRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
