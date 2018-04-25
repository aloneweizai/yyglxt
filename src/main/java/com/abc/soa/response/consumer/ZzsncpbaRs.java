package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.ZzsncpbaBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class ZzsncpbaRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<ZzsncpbaBO> zzsncpbaList;
    private ZzsncpbaBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<ZzsncpbaBO> getZzsncpbaList() {
		return zzsncpbaList;
	}

	public void setZzsncpbaList(List<ZzsncpbaBO> zzsncpbaList) {
		this.zzsncpbaList = zzsncpbaList;
	}

	public ZzsncpbaBO getData() {
		return data;
	}

	public void setData(ZzsncpbaBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
