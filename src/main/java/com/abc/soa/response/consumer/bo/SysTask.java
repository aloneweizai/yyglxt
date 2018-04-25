package com.abc.soa.response.consumer.bo;

import java.util.Date;

import com.abc.common.soa.response.BaseResponse;

public class SysTask extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;//任务ID
    private String name;//任务名称
    private String imageUrl;//图片地址
    private String startT;//开始时间
    private String endT;//结束时间
    
	private Date startTime;//开始时间
    private Date endTime;//结束时间
    private String rule;//规则
    private Integer points;//积分
    private Boolean status;//状态
    private Date createTime;//创建时间
    private Date lastUpdate;//更新时间
    private String type;//类型
    private String ruleName;//规则名称
    private String ruleCode;//规则code
    private Integer count;//次数
    private String skipURL;//跳转地址
    
    private String ruleId;//规则ID
    private String award;//奖励积分/经验值
    private String dateType;//时间类型：日：D;月：M;年：Y;一次性：O;无限制：A
    private String awardType; //奖励类型:0:经验值，1：积分
    private String code;//任务编码
    
    
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getAwardType() {
		return awardType;
	}
	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}
	public String getStartT() {
		return startT;
	}
	public void setStartT(String startT) {
		this.startT = startT;
	}
	public String getEndT() {
		return endT;
	}
	public void setEndT(String endT) {
		this.endT = endT;
	}
    
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getSkipURL() {
		return skipURL;
	}
	public void setSkipURL(String skipURL) {
		this.skipURL = skipURL;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
    
    
}
