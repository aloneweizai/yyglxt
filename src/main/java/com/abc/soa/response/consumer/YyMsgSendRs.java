package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.YyMsgSendBO;

import java.util.List;

/**
 * 用户查询返回类
 * Created by zhushuai on 2017/6/14
 *
 */
public class YyMsgSendRs extends BaseResponse{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int total;

    private List<YyMsgSendBO> dataList;

	private YyMsgSendBO data;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public List<YyMsgSendBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<YyMsgSendBO> dataList) {
		this.dataList = dataList;
	}

	public YyMsgSendBO getData() {
		return data;
	}

	public void setData(YyMsgSendBO data) {
		this.data = data;
	}
}
