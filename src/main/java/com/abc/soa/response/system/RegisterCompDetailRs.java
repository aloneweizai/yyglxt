package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.RegisterCompDetailBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class RegisterCompDetailRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<RegisterCompDetailBO> dataList;
    private RegisterCompDetailBO data;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<RegisterCompDetailBO> getDataList() {
		return dataList;
	}
	public void setDataList(List<RegisterCompDetailBO> dataList) {
		this.dataList = dataList;
	}
	public RegisterCompDetailBO getData() {
		return data;
	}
	public void setData(RegisterCompDetailBO data) {
		this.data = data;
	}
	
    
}
