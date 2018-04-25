package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author zhushuai
 *
 */
public class UpdatePhoneLog implements Serializable{

	/**
	 * 主键
	 */
	private String id;
	/**
	 *用户ID
	 */
	private String userId;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 用户头像
	 */
	private String userPicturePath;
	/**
	 *管理员ID
	 */
	private String adminId;
	/**
	 * 管理员昵称
	 */
	private String adminNickname;
	/**
	 *旧手机号码
	 */
	private String oldPhone;
	/**
	 *新手机号码
	 */
	private String newPhone;
	/**
	 *记日志时间
	 */
	private Date createTime;
	/**
	 *原因
	 */
	private String reason;

	private String newUsername;

	private String oldUsername;
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminNickname() {
		return adminNickname;
	}

	public void setAdminNickname(String adminNickname) {
		this.adminNickname = adminNickname;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOldPhone() {
		return oldPhone;
	}

	public void setOldPhone(String oldPhone) {
		this.oldPhone = oldPhone;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPicturePath() {
		return userPicturePath;
	}

	public void setUserPicturePath(String userPicturePath) {
		this.userPicturePath = userPicturePath;
	}

	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	public String getOldUsername() {
		return oldUsername;
	}

	public void setOldUsername(String oldUsername) {
		this.oldUsername = oldUsername;
	}
}
