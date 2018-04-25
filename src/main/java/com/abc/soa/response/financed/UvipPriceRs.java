package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.UvipPrice;

public class UvipPriceRs extends BaseResponse{
	private static final long serialVersionUID = -7859370887990688693L;
	private int total;

    private List<UvipPrice> dataList;
    private UvipPrice data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<UvipPrice> getDataList() {
		return dataList;
	}
	public void setDataList(List<UvipPrice> dataList) {
		this.dataList = dataList;
	}
	public UvipPrice getData() {
		return data;
	}
	public void setData(UvipPrice data) {
		this.data = data;
	}
    
    
}
