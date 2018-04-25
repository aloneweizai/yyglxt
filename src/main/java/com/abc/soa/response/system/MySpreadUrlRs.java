package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;

public class MySpreadUrlRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private String qrcode;
	private String spreadurl;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getSpreadurl() {
		return spreadurl;
	}

	public void setSpreadurl(String spreadurl) {
		this.spreadurl = spreadurl;
	}
}
