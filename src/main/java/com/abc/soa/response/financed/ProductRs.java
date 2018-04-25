package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Product;

public class ProductRs extends BaseResponse {
	private static final long serialVersionUID = 1L;

	private int total;

	private List<Product> dataList;
	private Product data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Product> getDataList() {
		return dataList;
	}

	public void setDataList(List<Product> dataList) {
		this.dataList = dataList;
	}

	public Product getData() {
		return data;
	}

	public void setData(Product data) {
		this.data = data;
	}

}
