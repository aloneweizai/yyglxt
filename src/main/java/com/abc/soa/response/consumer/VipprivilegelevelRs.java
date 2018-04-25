package com.abc.soa.response.consumer;

import java.util.List;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.VipPrivilegeLevelUpdateBO;

public class VipprivilegelevelRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<VipPrivilegeLevelUpdateBO> dataList;
    public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<VipPrivilegeLevelUpdateBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<VipPrivilegeLevelUpdateBO> dataList) {
		this.dataList = dataList;
	}
	public VipPrivilegeLevelUpdateBO getData() {
		return data;
	}
	public void setData(VipPrivilegeLevelUpdateBO data) {
		this.data = data;
	}
	private VipPrivilegeLevelUpdateBO data;
    
    
}
