package com.abc.soa.request.consumer;


/**
 * 用户查询类
 * Created by zhushuai on 2017/6/14
 * 
 */
public class YyMsgSendRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String name;
	private String createTime;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
