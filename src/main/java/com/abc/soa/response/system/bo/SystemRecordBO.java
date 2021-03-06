package com.abc.soa.response.system.bo;

import java.util.Date;

/**
 * Admin: lingsuzhi <554600654@qq.com.com> Date: 2017-08-16
 */

public class SystemRecordBO {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/** PK */
	private String id;
	/** 用户ID */
	private String userId;
	/** 会话ID */
	private String sessionId;
	/** 浏览时间 */
	private Date browseDate;
	/** 年 */
	private String year;
	/** 月 */
	private String month;
	/** 周 */
	private String week;
	/** 日 */
	private String day;
	/** 时 */
	private String hour;
	/** 分 */
	private String minute;
	/** 浏览页面TITLE */
	private String pageTitle;
	/** 浏览页面路径 */
	private String pageUrl;
	/** REFERER路径 */
	private String referer;
	/** 停留时长，单位：秒 */
	private Integer stayLong;
	/** 操作功能 */
	private String feature;
	/** 使用系统 */
	private String appName;
	/** 访问IP */
	private String ip;
	/** 访问地点 */
	private String location;
	/** 浏览器类型 */
	private String browseType;
	/** 浏览器版本 */
	private String browseVersion;
	/** 操作系统类型 */
	private String os;
	/** 设备类型 */
	private String device;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private Date createTime;

	private String nsrsbh;
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
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getBrowseDate() {
		return browseDate;
	}
	public void setBrowseDate(Date browseDate) {
		this.browseDate = browseDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public Integer getStayLong() {
		return stayLong;
	}
	public void setStayLong(Integer stayLong) {
		this.stayLong = stayLong;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBrowseType() {
		return browseType;
	}
	public void setBrowseType(String browseType) {
		this.browseType = browseType;
	}
	public String getBrowseVersion() {
		return browseVersion;
	}
	public void setBrowseVersion(String browseVersion) {
		this.browseVersion = browseVersion;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
}
