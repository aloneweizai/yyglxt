package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.ZzszfjgBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class ZzszfjgRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<ZzszfjgBO> zzszfjgList;
    private ZzszfjgBO data;
	private String msg;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<ZzszfjgBO> getZzszfjgList() {
		return zzszfjgList;
	}

	public void setZzszfjgList(List<ZzszfjgBO> zzszfjgList) {
		this.zzszfjgList = zzszfjgList;
	}

	public ZzszfjgBO getData() {
		return data;
	}

	public void setData(ZzszfjgBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
