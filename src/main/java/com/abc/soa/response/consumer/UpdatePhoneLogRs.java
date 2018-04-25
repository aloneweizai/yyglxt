package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.UpdatePhoneLog;

import java.util.List;

/**
 * 
 * @author zhushuai
 *
 */
public class UpdatePhoneLogRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<UpdatePhoneLog> dataList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<UpdatePhoneLog> getDataList() {
		return dataList;
	}

	public void setDataList(List<UpdatePhoneLog> dataList) {
		this.dataList = dataList;
	}
    
}
