package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoiceExpressExcel;

public class InvoiceExpressExcelRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;
	private InvoiceExpressExcel data;
	private List<InvoiceExpressExcel> dataList;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public InvoiceExpressExcel getData() {
		return data;
	}
	public void setData(InvoiceExpressExcel data) {
		this.data = data;
	}
	public List<InvoiceExpressExcel> getDataList() {
		return dataList;
	}
	public void setDataList(List<InvoiceExpressExcel> dataList) {
		this.dataList = dataList;
	}
	
	
	

}
