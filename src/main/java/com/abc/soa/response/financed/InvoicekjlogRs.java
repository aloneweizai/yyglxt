package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoicekjlogBO;

import java.util.List;

public class InvoicekjlogRs extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private int total;

    private List<InvoicekjlogBO> dataList;
    private InvoicekjlogBO data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<InvoicekjlogBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<InvoicekjlogBO> dataList) {
		this.dataList = dataList;
	}

	public InvoicekjlogBO getData() {
		return data;
	}

	public void setData(InvoicekjlogBO data) {
		this.data = data;
	}
}
