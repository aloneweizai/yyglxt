package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoiceRepo;

public class InvoiceRepoRs extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private int total;

    private List<InvoiceRepo> dataList;
    private InvoiceRepo data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<InvoiceRepo> getDataList() {
		return dataList;
	}
	public void setDataList(List<InvoiceRepo> dataList) {
		this.dataList = dataList;
	}
	public InvoiceRepo getData() {
		return data;
	}
	public void setData(InvoiceRepo data) {
		this.data = data;
	}
    
    

}
