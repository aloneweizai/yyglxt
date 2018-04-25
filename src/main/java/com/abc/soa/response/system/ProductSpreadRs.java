package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.ProductSpreadBO;

import java.util.List;

public class ProductSpreadRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private ProductSpreadBO data;
	private List<ProductSpreadBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ProductSpreadBO getData() {
		return data;
	}
	public void setData(ProductSpreadBO data) {
		this.data = data;
	}
	public List<ProductSpreadBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<ProductSpreadBO> dataList) {
		this.dataList = dataList;
	}
	
	
}
