package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.WeiXinPayquery;

import java.util.List;

/**
 * 支付宝信息交易查询返回类
 * @author zhushuai 2017-8-7
 *
 */
public class WeiXinPayqueryRes extends BaseResponse{
	private static final long serialVersionUID = -7859370887990688693L;
	private int total;

    private List<WeiXinPayquery> dataList;
    private WeiXinPayquery data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<WeiXinPayquery> getDataList() {
		return dataList;
	}
	public void setDataList(List<WeiXinPayquery> dataList) {
		this.dataList = dataList;
	}
	public WeiXinPayquery getData() {
		return data;
	}
	public void setData(WeiXinPayquery data) {
		this.data = data;
	}
    
    
}
