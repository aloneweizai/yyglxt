package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Invoice;


public class InvoiceRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;

    private List<Invoice> dataList;
    private Invoice data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Invoice> getDataList() {
		return dataList;
	}
	public void setDataList(List<Invoice> dataList) {
		this.dataList = dataList;
	}
	public Invoice getData() {
		return data;
	}
	public void setData(Invoice data) {
		this.data = data;
	}
    
    

}
