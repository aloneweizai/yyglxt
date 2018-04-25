package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.PointsOrExpLog;
/**
 * 
 * @author zhushuai
 *
 */
public class PointsOrExpLogRs extends BaseResponse{
	private static final long serialVersionUID = 1L;

	private int total;

    private List<PointsOrExpLog> dataList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<PointsOrExpLog> getDataList() {
		return dataList;
	}

	public void setDataList(List<PointsOrExpLog> dataList) {
		this.dataList = dataList;
	}
    
}
