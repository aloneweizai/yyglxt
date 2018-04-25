package com.abc.soa.response.vipGift;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.vipGift.bo.UgiftApplyBO;
/**
 * 会员礼包申请返回类
 * @author Administrator
 *
 */
public class UgiftApplyRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;// 数量

	private List<UgiftApplyBO> dataList; // 集合
	private UgiftApplyBO data; // 单个信息

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<UgiftApplyBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<UgiftApplyBO> dataList) {
		this.dataList = dataList;
	}

	public UgiftApplyBO getData() {
		return data;
	}

	public void setData(UgiftApplyBO data) {
		this.data = data;
	}

}
