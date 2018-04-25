package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 * Created by zhushuai on 2017/6/14
 *
 */
public class MsgSendDetailBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	private String userId;

	private String type;//推送渠道

	private Date createTime;

	private String nickName;

	private String messageId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
