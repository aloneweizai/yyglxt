package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Goodscategory;

public class GoodscategoryRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;

    private List<Goodscategory> dataList;
    private Goodscategory data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Goodscategory> getDataList() {
		return dataList;
	}
	public void setDataList(List<Goodscategory> dataList) {
		this.dataList = dataList;
	}
	public Goodscategory getData() {
		return data;
	}
	public void setData(Goodscategory data) {
		this.data = data;
	}
    
    
    
    
}
