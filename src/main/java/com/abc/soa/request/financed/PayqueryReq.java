package com.abc.soa.request.financed;

import java.io.Serializable;

public class PayqueryReq implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String out_trade_no;
    private String trade_no;
	private String transaction_id;
	private String refund_id;
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}


	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
}
