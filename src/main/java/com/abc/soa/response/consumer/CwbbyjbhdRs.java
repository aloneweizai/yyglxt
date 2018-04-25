package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.CwbbyjbhdBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class CwbbyjbhdRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<CwbbyjbhdBO> cwbbyjbhdList;
    private CwbbyjbhdBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<CwbbyjbhdBO> getCwbbyjbhdList() {
		return cwbbyjbhdList;
	}

	public void setCwbbyjbhdList(List<CwbbyjbhdBO> cwbbyjbhdList) {
		this.cwbbyjbhdList = cwbbyjbhdList;
	}

	public CwbbyjbhdBO getData() {
		return data;
	}

	public void setData(CwbbyjbhdBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
