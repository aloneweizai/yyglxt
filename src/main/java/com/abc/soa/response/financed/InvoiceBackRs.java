package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoiceBack;

public class InvoiceBackRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;

    private List<InvoiceBack> dataList;
    private InvoiceBack data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<InvoiceBack> getDataList() {
		return dataList;
	}
	public void setDataList(List<InvoiceBack> dataList) {
		this.dataList = dataList;
	}
	public InvoiceBack getData() {
		return data;
	}
	public void setData(InvoiceBack data) {
		this.data = data;
	}

    
}
