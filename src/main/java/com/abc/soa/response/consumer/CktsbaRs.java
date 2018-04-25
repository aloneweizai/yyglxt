package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.CktsbaBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class CktsbaRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<CktsbaBO> cktsbaList;
    private CktsbaBO data;
	private String msg;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<CktsbaBO> getCktsbaList() {
		return cktsbaList;
	}

	public void setCktsbaList(List<CktsbaBO> cktsbaList) {
		this.cktsbaList = cktsbaList;
	}

	public CktsbaBO getData() {
		return data;
	}

	public void setData(CktsbaBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
