package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.CouponActivity;
import com.abc.soa.response.financed.bo.CouponActivityBO;
import com.abc.soa.response.financed.bo.CouponActivityListBO;

import java.util.List;

public class CouponActivityRs extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private int total;

    private List<CouponActivityListBO> dataList;
    private CouponActivity data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<CouponActivityListBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<CouponActivityListBO> dataList) {
		this.dataList = dataList;
	}
	public CouponActivity getData() {
		return data;
	}
	public void setData(CouponActivity data) {
		this.data = data;
	}

    

}
