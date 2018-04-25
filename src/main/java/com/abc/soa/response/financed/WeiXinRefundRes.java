package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.WeiXinRefund;

import java.util.List;

/**
 * 支付宝信息交易查询返回类
 * @author zhushuai 2017-8-7
 *
 */
public class WeiXinRefundRes extends BaseResponse{
	private static final long serialVersionUID = -7859370887990688693L;
	private int total;

    private List<WeiXinRefund> dataList;
    private WeiXinRefund data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<WeiXinRefund> getDataList() {
		return dataList;
	}
	public void setDataList(List<WeiXinRefund> dataList) {
		this.dataList = dataList;
	}
	public WeiXinRefund getData() {
		return data;
	}
	public void setData(WeiXinRefund data) {
		this.data = data;
	}
    
    
}
