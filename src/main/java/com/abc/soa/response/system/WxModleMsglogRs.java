package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.WxModleMsglogBO;

import java.util.List;

public class WxModleMsglogRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private WxModleMsglogBO data;
	private List<WxModleMsglogBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public WxModleMsglogBO getData() {
		return data;
	}
	public void setData(WxModleMsglogBO data) {
		this.data = data;
	}
	public List<WxModleMsglogBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<WxModleMsglogBO> dataList) {
		this.dataList = dataList;
	}
	
	
}
