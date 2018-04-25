package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.CwbbbaBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class CwbbbaRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<CwbbbaBO> cwbbbaList;
    private CwbbbaBO data;
	private String msg;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<CwbbbaBO> getCwbbbaList() {
		return cwbbbaList;
	}

	public void setCwbbbaList(List<CwbbbaBO> cwbbbaList) {
		this.cwbbbaList = cwbbbaList;
	}

	public CwbbbaBO getData() {
		return data;
	}

	public void setData(CwbbbaBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
