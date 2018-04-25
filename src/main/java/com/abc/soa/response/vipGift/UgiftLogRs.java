package com.abc.soa.response.vipGift;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.vipGift.bo.UgiftLog;

/**
 * 会员礼包申请日志
 * 
 * @author Administrator
 *
 */
public class UgiftLogRs extends BaseResponse {

	private static final long serialVersionUID = -7859370887990688693L;
	private int total;// 数量

	private List<UgiftLog> dataList; // 集合
	private UgiftLog data; // 单个信息

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<UgiftLog> getDataList() {
		return dataList;
	}

	public void setDataList(List<UgiftLog> dataList) {
		this.dataList = dataList;
	}

	public UgiftLog getData() {
		return data;
	}

	public void setData(UgiftLog data) {
		this.data = data;
	}

}
