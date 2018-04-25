package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Productrepo;

public class ProductrepoRs extends BaseResponse {
	private static final long serialVersionUID = 1L;

	private int total;

	private List<Productrepo> dataList;
	private Productrepo data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Productrepo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Productrepo> dataList) {
		this.dataList = dataList;
	}

	public Productrepo getData() {
		return data;
	}

	public void setData(Productrepo data) {
		this.data = data;
	}

}
