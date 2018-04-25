package com.abc.soa.response.financed;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Payquery;
/**
 * 支付宝信息交易查询返回类
 * @author zhushuai 2017-8-7
 *
 */
public class PayqueryRes extends BaseResponse{
	private static final long serialVersionUID = -7859370887990688693L;
	private int total;

    private List<Payquery> dataList;
    private Payquery data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Payquery> getDataList() {
		return dataList;
	}
	public void setDataList(List<Payquery> dataList) {
		this.dataList = dataList;
	}
	public Payquery getData() {
		return data;
	}
	public void setData(Payquery data) {
		this.data = data;
	}
    
    
}
