package com.abc.soa.response.consumer.bo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author zhushuai
 *
 */
public class PointsOrExpLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2914040931178809698L;
    private String id;
    private String userId;
    private String ruleId;
    private String name;
    private String code;
    private String type;
    private Integer income;
    private Integer outgo;
    private Integer usablePoints;
    private Integer usableExp;
    private Date createTime;
	private Integer usable;
	private String remark;
	private String businessId;
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
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getOutgo() {
		return outgo;
	}
	public void setOutgo(Integer outgo) {
		this.outgo = outgo;
	}
	public Integer getUsablePoints() {
		return usablePoints;
	}
	public void setUsablePoints(Integer usablePoints) {
		this.usablePoints = usablePoints;
	}
	public Integer getUsableExp() {
		return usableExp;
	}
	public void setUsableExp(Integer usableExp) {
		this.usableExp = usableExp;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUsable() {
		return usable;
	}

	public void setUsable(Integer usable) {
		this.usable = usable;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
}
