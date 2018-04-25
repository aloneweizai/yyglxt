package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.AliCode;

public class AliCodeRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private AliCode data;

	public AliCode getData() {
		return data;
	}

	public void setData(AliCode data) {
		this.data = data;
	}
}
