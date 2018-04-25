package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.PointRule;
/**
 * 
 * @author zhushuai
 *
 */
public class PointRuleRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<PointRule> dataList;
    private PointRule data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<PointRule> getDataList() {
		return dataList;
	}

	public void setDataList(List<PointRule> dataList) {
		this.dataList = dataList;
	}

	public PointRule getData() {
		return data;
	}

	public void setData(PointRule data) {
		this.data = data;
	}
    
}
