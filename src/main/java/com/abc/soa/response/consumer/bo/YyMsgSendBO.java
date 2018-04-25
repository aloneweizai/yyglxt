package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 * Created by zhushuai on 2017/6/14
 *
 */
public class YyMsgSendBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	private String taskname;//任务名称

	private Boolean web;//是否发送系统消息

	private Boolean wechat;//是否发送微信

	private Boolean message;//是否发送短信

	private String content;//消息内容
	private String url;

	private String urltitle;

	private String urlhref;

	private String target;//目标人群：1-全部用户，2-部分用户，3-特定用户

	private String sendTime;//推送时间: 1-立即推送，2-定时推送

	private String startTime;

	private String endTime;//结束时间
	//状态：0-已撤销，1-进行中，2-已完成
	private String status;
	private Date createTime;
	private Date lastUpdate;
	private String areaOper;//区域操作符：等于(equals), 不等于(ne)
	private String areaIds;
	private String tagOper;//标签操作符：等于(equals), 不等于(ne)
	private String tagIds;
	private String regTimeOper;//注册时间操作符：小于等于(lte)，大于等于(gte)，时间段(between)

	private String regStartTime;

	private String regEndTime;
	private String userIds;
	//频率
	private String rate;

	private String tagName;

	private String userName;

	private Integer num;
	private Integer tagnum;
	private Integer usernum;
	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	public String getAreaOper() {
		return areaOper;
	}

	public void setAreaOper(String areaOper) {
		this.areaOper = areaOper;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Boolean getMessage() {
		return message;
	}

	public void setMessage(Boolean message) {
		this.message = message;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRegEndTime() {
		return regEndTime;
	}

	public void setRegEndTime(String regEndTime) {
		this.regEndTime = regEndTime;
	}

	public String getRegStartTime() {
		return regStartTime;
	}

	public void setRegStartTime(String regStartTime) {
		this.regStartTime = regStartTime;
	}

	public String getRegTimeOper() {
		return regTimeOper;
	}

	public void setRegTimeOper(String regTimeOper) {
		this.regTimeOper = regTimeOper;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getTagOper() {
		return tagOper;
	}

	public void setTagOper(String tagOper) {
		this.tagOper = tagOper;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Boolean getWeb() {
		return web;
	}

	public void setWeb(Boolean web) {
		this.web = web;
	}

	public Boolean getWechat() {
		return wechat;
	}

	public void setWechat(Boolean wechat) {
		this.wechat = wechat;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getUrltitle() {
		return urltitle;
	}

	public void setUrltitle(String urltitle) {
		this.urltitle = urltitle;
	}

	public String getUrlhref() {
		return urlhref;
	}

	public void setUrlhref(String urlhref) {
		this.urlhref = urlhref;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getTagnum() {
		return tagnum;
	}

	public void setTagnum(Integer tagnum) {
		this.tagnum = tagnum;
	}

	public Integer getUsernum() {
		return usernum;
	}

	public void setUsernum(Integer usernum) {
		this.usernum = usernum;
	}
}
