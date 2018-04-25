package com.abc.soa.request.consumer;


/**
 * 用户查询类
 * Created by zhushuai on 2017/6/14
 * 
 */
public class ConsumerRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String nickname;
	private String phone;
	private Boolean status;
	private String tagName;

	private String exp;
	private String points;
	private String vipLevel;
	private String realName;
	private String medal;
	private String tagId;
	private String createTime;
	private String startDate;
	private String endDate;
	private String datetype;
	private String pointsOper;
	private String expOper;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMedal() {
		return medal;
	}

	public void setMedal(String medal) {
		this.medal = medal;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDatetype() {
		return datetype;
	}

	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}

	public String getPointsOper() {
		return pointsOper;
	}

	public void setPointsOper(String pointsOper) {
		this.pointsOper = pointsOper;
	}

	public String getExpOper() {
		return expOper;
	}

	public void setExpOper(String expOper) {
		this.expOper = expOper;
	}
}
