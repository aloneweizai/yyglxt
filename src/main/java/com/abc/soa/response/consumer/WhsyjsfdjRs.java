package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.WhsyjsfdjBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class WhsyjsfdjRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<WhsyjsfdjBO> whsyjsfList;
    private WhsyjsfdjBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}


	public List<WhsyjsfdjBO> getWhsyjsfList() {
		return whsyjsfList;
	}

	public void setWhsyjsfList(List<WhsyjsfdjBO> whsyjsfList) {
		this.whsyjsfList = whsyjsfList;
	}

	public WhsyjsfdjBO getData() {
		return data;
	}

	public void setData(WhsyjsfdjBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
