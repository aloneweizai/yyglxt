package com.abc.soa.response.system.bo;

/**
 * Admin: lingsuzhi <554600654@qq.com.com> Date: 2017-08-16
 */

public class UserMessageBO {
	private String id;
	//发送人ID
	private String fromUserId;
	//接收人ID
	private String toUserId;
	//私信内容
	private String content;
	//私信状态
	private String status;

	private String createTime;

	private String lastUpdate;

	//私信类型
	private String type;

	//发送人用户名
	private String fromNickname;

	//发送人头像图片地址
	private String fromUserPic;

	//接收人用户名
	private String toNickname;

	//接收人人头像图片地址
	private String toUserPic;

	private String contentstr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getContentstr() {
		return contentstr;
	}

	public void setContentstr(String contentstr) {
		this.contentstr = contentstr;
	}


	public String getFromUserPic() {
		return fromUserPic;
	}

	public void setFromUserPic(String fromUserPic) {
		this.fromUserPic = fromUserPic;
	}

	public String getToUserPic() {
		return toUserPic;
	}

	public void setToUserPic(String toUserPic) {
		this.toUserPic = toUserPic;
	}

	public String getToNickname() {
		return toNickname;
	}

	public void setToNickname(String toNickname) {
		this.toNickname = toNickname;
	}

	public String getFromNickname() {
		return fromNickname;
	}

	public void setFromNickname(String fromNickname) {
		this.fromNickname = fromNickname;
	}
}
