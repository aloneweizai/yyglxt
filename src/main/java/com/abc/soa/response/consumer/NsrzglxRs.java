package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.NsrzglxBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class NsrzglxRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<NsrzglxBO> nsrzgList;
    private NsrzglxBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<NsrzglxBO> getNsrzgList() {
		return nsrzgList;
	}

	public void setNsrzgList(List<NsrzglxBO> nsrzgList) {
		this.nsrzgList = nsrzgList;
	}

	public NsrzglxBO getData() {
		return data;
	}

	public void setData(NsrzglxBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
