package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.MobileModleMsgBO;

import java.util.List;

public class MobileModleMsgRs extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private MobileModleMsgBO data;
	private List<MobileModleMsgBO> dataList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public MobileModleMsgBO getData() {
		return data;
	}
	public void setData(MobileModleMsgBO data) {
		this.data = data;
	}
	public List<MobileModleMsgBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<MobileModleMsgBO> dataList) {
		this.dataList = dataList;
	}
	
	
}
