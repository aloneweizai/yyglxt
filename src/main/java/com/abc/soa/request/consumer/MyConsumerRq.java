package com.abc.soa.request.consumer;


/**
 * 用户查询类
 * Created by zhushuai on 2017/6/14
 * 
 */
public class MyConsumerRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String nickname;
	private String phone;
	private String realname;
	private String createTimeB;
	private String createTimeE;
    private String recommend;
	private String datetype;
	private String recommendPhone;
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getDatetype() {
		return datetype;
	}

	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}

	public String getCreateTimeB() {
		return createTimeB;
	}

	public void setCreateTimeB(String createTimeB) {
		this.createTimeB = createTimeB;
	}

	public String getCreateTimeE() {
		return createTimeE;
	}

	public void setCreateTimeE(String createTimeE) {
		this.createTimeE = createTimeE;
	}

	public String getRecommendPhone() {
		return recommendPhone;
	}

	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}
}
