package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.Consumer;
/**
 * 用户查询返回类
 * Created by zhushuai on 2017/6/14
 * 
 */
public class ConsumerRs extends BaseResponse{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;

    private List<Consumer> dataList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Consumer> getDataList() {
		return dataList;
	}

	public void setDataList(List<Consumer> dataList) {
		this.dataList = dataList;
	}
    
    
}
