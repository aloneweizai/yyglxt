package com.abc.soa.response.vipGift;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.vipGift.bo.Gift;
/**
 * 会员礼物请求接收类
 * @author Administrator
 *
 */
public class VipGiftRs extends BaseResponse {
	/**
	*
	*/
	private static final long serialVersionUID = -7859370887990688693L;
	private int total;//数量

	private List<Gift> dataList; //集合
	private Gift data; //单个信息

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Gift> getDataList() {
		return dataList;
	}

	public void setDataList(List<Gift> dataList) {
		this.dataList = dataList;
	}

	public Gift getData() {
		return data;
	}

	public void setData(Gift data) {
		this.data = data;
	}

}
