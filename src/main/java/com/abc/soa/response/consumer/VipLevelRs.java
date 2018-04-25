package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.VipLevel;
/**
 * 
 * @author zhushuai
 *
 */
public class VipLevelRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<VipLevel> dataList;
    private VipLevel data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<VipLevel> getDataList() {
		return dataList;
	}

	public void setDataList(List<VipLevel> dataList) {
		this.dataList = dataList;
	}

	public VipLevel getData() {
		return data;
	}

	public void setData(VipLevel data) {
		this.data = data;
	}
    
}
