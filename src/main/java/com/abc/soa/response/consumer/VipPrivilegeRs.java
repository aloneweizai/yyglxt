package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.VipPrivilege;
/**
 * 
 * @author zhushuai
 *
 */
public class VipPrivilegeRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<VipPrivilege> dataList;
    private VipPrivilege data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<VipPrivilege> getDataList() {
		return dataList;
	}

	public void setDataList(List<VipPrivilege> dataList) {
		this.dataList = dataList;
	}

	public VipPrivilege getData() {
		return data;
	}

	public void setData(VipPrivilege data) {
		this.data = data;
	}
    
}
