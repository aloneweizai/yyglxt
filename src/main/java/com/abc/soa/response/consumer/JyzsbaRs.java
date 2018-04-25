package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.JyzsbaBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class JyzsbaRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<JyzsbaBO> jyzsbaList;
    private JyzsbaBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<JyzsbaBO> getJyzsbaList() {
		return jyzsbaList;
	}

	public void setJyzsbaList(List<JyzsbaBO> jyzsbaList) {
		this.jyzsbaList = jyzsbaList;
	}

	public JyzsbaBO getData() {
		return data;
	}

	public void setData(JyzsbaBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
