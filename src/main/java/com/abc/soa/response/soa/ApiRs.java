package com.abc.soa.response.soa;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.soa.bo.Api;
/**
 * API接口返回类
 * @author 2017-11-6
 *
 */
public class ApiRs extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total;//总条数

    private List<Api> dataList; //集合
    private Api data; //单个对象
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Api> getDataList() {
		return dataList;
	}
	public void setDataList(List<Api> dataList) {
		this.dataList = dataList;
	}
	public Api getData() {
		return data;
	}
	public void setData(Api data) {
		this.data = data;
	}
    
    
}
