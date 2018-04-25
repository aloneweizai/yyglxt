package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.Consumer;
import com.abc.soa.response.consumer.bo.ConsumerExtend;
/**
 * 用户详细信息返回
 * @author zhushuai
 *
 */
public class ConsumerInfoRs extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Consumer data;
	private Consumer user;
	private ConsumerExtend user_extend;
	public Consumer getUser() {
		return user;
	}
	public void setUser(Consumer user) {
		this.user = user;
	}
	public ConsumerExtend getUser_extend() {
		return user_extend;
	}
	public void setUser_extend(ConsumerExtend user_extend) {
		this.user_extend = user_extend;
	}

	public Consumer getData() {
		return data;
	}

	public ConsumerInfoRs setData(Consumer data) {
		this.data = data;
		return this;
	}
}
