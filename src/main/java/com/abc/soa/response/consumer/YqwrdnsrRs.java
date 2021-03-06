package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.YqwrdnsrBO;

import java.util.List;

/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class YqwrdnsrRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int total;
    private List<YqwrdnsrBO> yqwrdList;
    private YqwrdnsrBO data;

	private String msg;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}


	public List<YqwrdnsrBO> getYqwrdList() {
		return yqwrdList;
	}

	public void setYqwrdList(List<YqwrdnsrBO> yqwrdList) {
		this.yqwrdList = yqwrdList;
	}

	public YqwrdnsrBO getData() {
		return data;
	}

	public void setData(YqwrdnsrBO data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
