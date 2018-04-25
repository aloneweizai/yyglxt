package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.TaxpayerBindPwdLog;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class TaxpayerBindPwdLogRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<TaxpayerBindPwdLog> dataList;
    private TaxpayerBindPwdLog data;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<TaxpayerBindPwdLog> getDataList() {
		return dataList;
	}
	public void setDataList(List<TaxpayerBindPwdLog> dataList) {
		this.dataList = dataList;
	}
	public TaxpayerBindPwdLog getData() {
		return data;
	}
	public void setData(TaxpayerBindPwdLog data) {
		this.data = data;
	}
	
    
}
