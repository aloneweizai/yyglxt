package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.SfExportBO;

public class SfExportRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

	private List<SfExportBO> dataList;
	private SfExportBO data;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<SfExportBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<SfExportBO> dataList) {
		this.dataList = dataList;
	}
	public SfExportBO getData() {
		return data;
	}
	public void setData(SfExportBO data) {
		this.data = data;
	}
	
	
}
