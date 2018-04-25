package com.abc.soa.response.soa;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.soa.bo.App;

public class AppRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;//总数

    private List<App> dataList; //集合
    private App data; //单个
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<App> getDataList() {
		return dataList;
	}
	public void setDataList(List<App> dataList) {
		this.dataList = dataList;
	}
	public App getData() {
		return data;
	}
	public void setData(App data) {
		this.data = data;
	}
    
    
}
