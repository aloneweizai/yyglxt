package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoiceLy;

import java.util.List;

public class InvoiceLyRs extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private int total;

    private List<InvoiceLy> dataList;
    private InvoiceLy data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<InvoiceLy> getDataList() {
		return dataList;
	}

	public void setDataList(List<InvoiceLy> dataList) {
		this.dataList = dataList;
	}

	public InvoiceLy getData() {
		return data;
	}

	public void setData(InvoiceLy data) {
		this.data = data;
	}
}
