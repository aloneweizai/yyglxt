package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.CouponUserListBO;

import java.util.List;

public class CouponUserRs extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private int total;

    private List<CouponUserListBO> dataList;
    private CouponUserListBO data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<CouponUserListBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<CouponUserListBO> dataList) {
		this.dataList = dataList;
	}
	public CouponUserListBO getData() {
		return data;
	}
	public void setData(CouponUserListBO data) {
		this.data = data;
	}
    
    

}
