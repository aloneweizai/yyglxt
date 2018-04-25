package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.CouponBO;
import com.abc.soa.response.financed.bo.CouponListBO;

import java.util.List;

public class CouponRs extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private int total;

    private List<CouponListBO> dataList;
    private CouponListBO data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<CouponListBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<CouponListBO> dataList) {
		this.dataList = dataList;
	}
	public CouponListBO getData() {
		return data;
	}
	public void setData(CouponListBO data) {
		this.data = data;
	}
    
    

}
