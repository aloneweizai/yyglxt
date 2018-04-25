package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.WeiXinCode;

public class WeiXinCodeRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private WeiXinCode data;

	public WeiXinCode getData() {
		return data;
	}

	public void setData(WeiXinCode data) {
		this.data = data;
	}
}
