package com.abc.soa.response.system.bo;

/**
 * Admin: lingsuzhi <554600654@qq.com.com> Date: 2017-08-16
 */

public class BusinessMessageBO {
	private String id;

	private String userId;

	private String businessId;

	private String content;

	private String contentstr;

	private String status;

	private String createTime;

	private String lastUpdate;

	private String type;

	//链接地址
	private String url;

	//消息业务类型
	private String busiType;

	private String username;

	private String userPicturePath;

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

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getContentstr() {
		return contentstr;
	}

	public void setContentstr(String contentstr) {
		this.contentstr = contentstr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPicturePath() {
		return userPicturePath;
	}

	public void setUserPicturePath(String userPicturePath) {
		this.userPicturePath = userPicturePath;
	}
}
