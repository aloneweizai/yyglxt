package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoiceExcel;


public class InvoiceExcelRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;
	private InvoiceExcel data;
	private List<InvoiceExcel> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public InvoiceExcel getData() {
		return data;
	}
	public void setData(InvoiceExcel data) {
		this.data = data;
	}
	public List<InvoiceExcel> getDataList() {
		return dataList;
	}
	public void setDataList(List<InvoiceExcel> dataList) {
		this.dataList = dataList;
	}
	
	
}
