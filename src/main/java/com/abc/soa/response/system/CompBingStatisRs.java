package com.abc.soa.response.system;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.CompBingStatisBO;

import java.util.List;

public class CompBingStatisRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;
	private List<CompBingStatisBO> data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<CompBingStatisBO> getData() {
		return data;
	}

	public void setData(List<CompBingStatisBO> data) {
		this.data = data;
	}
}
