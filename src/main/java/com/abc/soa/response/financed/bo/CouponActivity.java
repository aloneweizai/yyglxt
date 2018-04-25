package com.abc.soa.response.financed.bo;

import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;
import java.util.Date;


public class CouponActivity extends BaseResponse implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠劵活动ID
	 */
	private String id;

	/**
	 * 活动名称
	 */
	private String activityName;

	/**
	 * 活动链接
	 */
	private String activityLink;

	/**
	 * 优惠劵ID
	 */
	private String couponId;

	private String couponName;

	/**
	 * 活动开始时间
	 */
	private Date activityStartTime;

	/**
	 * 活动截止时间
	 */
	private Date activityEndTime;

	/**
	 * 优惠劵发放数量
	 */
	private Integer couponNum;

	/**
	 * 限制类型：true-限制，false-不限制
	 */
	private Boolean limit;

	/**
	 * 每人限制领取数量
	 */
	private Integer limitNum;

	/**
	 * 领取方式：系统派发-SYSTEM，用户领取-USER
	 */
	private String getType;

	/**
	 * 校验接口
	 */
	private String validApi;

	/**
	 * 是否校验接口
	 */
	private Boolean valid;

	/**
	 * 目标人群：1-全部用户，2-部分用户，3-特定用户
	 */
	private String target;

	/**
	 * 区域操作符：等于(equals), 不等于(ne)
	 */
	private String areaOper;

	/**
	 * 区域ID，逗号分隔
	 */
	private String areaIds;

	/**
	 * 标签操作符：等于(equals), 不等于(ne)
	 */
	private String tagOper;

	/**
	 * 标签ID，逗号分隔
	 */
	private String tagIds;

	/**
	 * 注册时间操作符：小于等于(lte)，大于等于(gte)，时间段(between)
	 */
	private String regTimeOper;

	/**
	 * 注册开始时间
	 */
	private Date regStartTime;

	/**
	 * 注册结束时间
	 */
	private Date regEndTime;

	/**
	 * 会员类型，逗号分隔
	 */
	private String vips;

	private String levels;

	/**
	 * 特定用户：用户ID，逗号分隔
	 */
	private String userIds;

	/**
	 * 活动描述
	 */
	private String description;

	/**
	 * 活动图片
	 */
	private String imageUrl;

	/**
	 * 活动状态: 0-删除 1-草稿 2-启用 3-停用
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date lastUpdate;

	private String tagName;

	private String userName;

	private Integer num;
	private Integer tagnum;
	private Integer usernum;
	private String start;
	private String end;
	private String validType;
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityLink() {
		return activityLink;
	}

	public void setActivityLink(String activityLink) {
		this.activityLink = activityLink;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Date getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(Date activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}

	public Boolean getLimit() {
		return limit;
	}

	public void setLimit(Boolean limit) {
		this.limit = limit;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getGetType() {
		return getType;
	}

	public void setGetType(String getType) {
		this.getType = getType;
	}

	public String getValidApi() {
		return validApi;
	}

	public void setValidApi(String validApi) {
		this.validApi = validApi;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getAreaOper() {
		return areaOper;
	}

	public void setAreaOper(String areaOper) {
		this.areaOper = areaOper;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	public String getTagOper() {
		return tagOper;
	}

	public void setTagOper(String tagOper) {
		this.tagOper = tagOper;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getRegTimeOper() {
		return regTimeOper;
	}

	public void setRegTimeOper(String regTimeOper) {
		this.regTimeOper = regTimeOper;
	}

	public Date getRegStartTime() {
		return regStartTime;
	}

	public void setRegStartTime(Date regStartTime) {
		this.regStartTime = regStartTime;
	}

	public Date getRegEndTime() {
		return regEndTime;
	}

	public void setRegEndTime(Date regEndTime) {
		this.regEndTime = regEndTime;
	}

	public String getVips() {
		return vips;
	}

	public void setVips(String vips) {
		this.vips = vips;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getValidType() {
		return validType;
	}

	public void setValidType(String validType) {
		this.validType = validType;
	}
}
