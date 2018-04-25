package com.abc.soa.request.consumer;


/**
 * 用户查询类
 * Created by zhushuai on 2017/6/14
 * 
 */
public class MsgSendDetailRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String nickName;
    private String id;
	private String messageId;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
