package com.abc.soa.response.financed.bo;

import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;


public class AliCode extends BaseResponse implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String out_trade_no;


	private String qccodeStr;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getQccodeStr() {
		return qccodeStr;
	}

	public void setQccodeStr(String qccodeStr) {
		this.qccodeStr = qccodeStr;
	}
}
