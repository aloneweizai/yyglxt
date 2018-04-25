package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.MsgSendDetailBO;

import java.util.List;

/**
 * 用户查询返回类
 * Created by zhushuai on 2017/6/14
 *
 */
public class MsgSendDetailRs extends BaseResponse{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int total;

    private List<MsgSendDetailBO> dataList;

	private MsgSendDetailBO data;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public List<MsgSendDetailBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<MsgSendDetailBO> dataList) {
		this.dataList = dataList;
	}

	public MsgSendDetailBO getData() {
		return data;
	}

	public void setData(MsgSendDetailBO data) {
		this.data = data;
	}
}
