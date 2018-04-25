package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.ProductBO;

public class ProductBORs extends BaseResponse{
	private static final long serialVersionUID = -7859370887990688693L;
	private int total;

    private List<ProductBO> dataList;
    private ProductBO data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<ProductBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<ProductBO> dataList) {
		this.dataList = dataList;
	}
	public ProductBO getData() {
		return data;
	}
	public void setData(ProductBO data) {
		this.data = data;
	}
    
    
}
