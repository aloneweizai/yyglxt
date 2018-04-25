package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.SdsdjhzBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class SdsdjhzRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<SdsdjhzBO> sdsdjhzList;
    private SdsdjhzBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<SdsdjhzBO> getSdsdjhzList() {
		return sdsdjhzList;
	}

	public void setSdsdjhzList(List<SdsdjhzBO> sdsdjhzList) {
		this.sdsdjhzList = sdsdjhzList;
	}

	public SdsdjhzBO getData() {
		return data;
	}

	public void setData(SdsdjhzBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
