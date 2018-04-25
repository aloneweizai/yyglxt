package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Shipping;

public class ShippingRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7859370887990688693L;
	private int total;

    private List<Shipping> dataList;
    private Shipping data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Shipping> getDataList() {
		return dataList;
	}
	public void setDataList(List<Shipping> dataList) {
		this.dataList = dataList;
	}
	public Shipping getData() {
		return data;
	}
	public void setData(Shipping data) {
		this.data = data;
	}
	
    
    
}
