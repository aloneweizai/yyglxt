package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoiceUseDetailBO;


public class InvoiceUseDetailRs  extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private int total;

	private InvoiceUseDetailBO data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public InvoiceUseDetailBO getData() {
		return data;
	}

	public void setData(InvoiceUseDetailBO data) {
		this.data = data;
	}
}
