package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.SmrzxxBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class SmrzxxRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<SmrzxxBO> dataList;
    private SmrzxxBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<SmrzxxBO> getDataList() {
		return dataList;
	}

	public void setDataList(List<SmrzxxBO> dataList) {
		this.dataList = dataList;
	}

	public SmrzxxBO getData() {
		return data;
	}

	public void setData(SmrzxxBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
