package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.SzhdxxBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class SzhdxxRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<SzhdxxBO> cwbbyjbhdList;
    private SzhdxxBO nsrjcxxvo;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<SzhdxxBO> getCwbbyjbhdList() {
		return cwbbyjbhdList;
	}

	public void setCwbbyjbhdList(List<SzhdxxBO> cwbbyjbhdList) {
		this.cwbbyjbhdList = cwbbyjbhdList;
	}

	public SzhdxxBO getNsrjcxxvo() {
		return nsrjcxxvo;
	}

	public void setNsrjcxxvo(SzhdxxBO nsrjcxxvo) {
		this.nsrjcxxvo = nsrjcxxvo;
	}
}
