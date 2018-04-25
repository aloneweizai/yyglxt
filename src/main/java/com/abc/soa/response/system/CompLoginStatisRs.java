package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.CompLoginStatisBO;

import java.util.List;

public class CompLoginStatisRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private List<CompLoginStatisBO> data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<CompLoginStatisBO> getData() {
		return data;
	}

	public void setData(List<CompLoginStatisBO> data) {
		this.data = data;
	}
}
