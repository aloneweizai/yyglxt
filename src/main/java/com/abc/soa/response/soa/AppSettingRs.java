package com.abc.soa.response.soa;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.soa.bo.AppSetting;

public class AppSettingRs extends BaseResponse{
	private static final long serialVersionUID = 1L;
	private int total;

    private List<AppSetting> dataList;
    private AppSetting data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<AppSetting> getDataList() {
		return dataList;
	}
	public void setDataList(List<AppSetting> dataList) {
		this.dataList = dataList;
	}
	public AppSetting getData() {
		return data;
	}
	public void setData(AppSetting data) {
		this.data = data;
	}
    
    
}
