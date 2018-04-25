package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoiceRepoDetail;

public class InvoiceRepoDetailRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;

    private List<InvoiceRepoDetail> dataList;
    private InvoiceRepoDetail data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<InvoiceRepoDetail> getDataList() {
		return dataList;
	}
	public void setDataList(List<InvoiceRepoDetail> dataList) {
		this.dataList = dataList;
	}
	public InvoiceRepoDetail getData() {
		return data;
	}
	public void setData(InvoiceRepoDetail data) {
		this.data = data;
	}
    
    
}
