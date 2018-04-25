package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.TaxpayerBind;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class TaxpayerBindRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<TaxpayerBind> dataList;
    private TaxpayerBind data;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<TaxpayerBind> getDataList() {
		return dataList;
	}
	public void setDataList(List<TaxpayerBind> dataList) {
		this.dataList = dataList;
	}
	public TaxpayerBind getData() {
		return data;
	}
	public void setData(TaxpayerBind data) {
		this.data = data;
	}
	
    
}
