package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.ExpRule;
/**
 * 
 * @author zhushuai
 *
 */
public class ExpRuleRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<ExpRule> dataList;
    private ExpRule data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ExpRule> getDataList() {
		return dataList;
	}

	public void setDataList(List<ExpRule> dataList) {
		this.dataList = dataList;
	}

	public ExpRule getData() {
		return data;
	}

	public void setData(ExpRule data) {
		this.data = data;
	}
    
}
