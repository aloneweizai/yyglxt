package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.JzjtzgBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class JzjtzgRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<JzjtzgBO> jzjtzgList;
    private JzjtzgBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<JzjtzgBO> getJzjtzgList() {
		return jzjtzgList;
	}

	public void setJzjtzgList(List<JzjtzgBO> jzjtzgList) {
		this.jzjtzgList = jzjtzgList;
	}

	public JzjtzgBO getData() {
		return data;
	}

	public void setData(JzjtzgBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
