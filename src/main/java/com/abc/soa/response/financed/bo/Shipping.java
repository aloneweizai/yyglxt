package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;

public class Shipping implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6482105145132662575L;
	private String id;
	/**
	 * 配送方式名称
	 **/
	private String name;
	/**
	 * 排序
	 **/
	private Integer sort;
	/**
	 * 启用状态：true|false
	 **/
	private Boolean status;
	/**
	 * 详细介绍
	 **/
	private String description;
	private Date lastUpdate;
	private Date createTime;
	/**
	 * 首重重量
	 **/
	private Double firstWeight;
	/**
	 * 首重费用
	 **/
	private Double firstWeightFee;
	/**
	 * 续重重量
	 **/
	private Double addedWeight;
	/**
	 * 续重费用
	 **/
	private Double addedWeightFee;
	/**
	 * 是否保价
	 **/
	private Integer isInsured;
	/**
	 * 保价费率
	 **/
	private Double rate;
	/**
	 * 最低保价费
	 **/
	private Double minInsuredFee;
	/**
	 * 地区运费类型：1.统一地区运费 2.指定地区运费
	 **/
	private String areaFeeType;
	private String type; //1:先收款后发货 2:货到付款
	
	

	public Double getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(Double firstWeight) {
		this.firstWeight = firstWeight;
	}

	public Double getFirstWeightFee() {
		return firstWeightFee;
	}

	public void setFirstWeightFree(Double firstWeightFee) {
		this.firstWeightFee = firstWeightFee;
	}

	public Double getAddedWeight() {
		return addedWeight;
	}

	public void setAddedWeight(Double addedWeight) {
		this.addedWeight = addedWeight;
	}

	public Double getAddedWeightFee() {
		return addedWeightFee;
	}

	public void setAddedWeightFree(Double addedWeightFee) {
		this.addedWeightFee = addedWeightFee;
	}

	public Integer getIsInsured() {
		return isInsured;
	}

	public void setIsInsured(Integer isInsured) {
		this.isInsured = isInsured;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getMinInsuredFee() {
		return minInsuredFee;
	}

	public void setMinInsuredFee(Double minInsuredFee) {
		this.minInsuredFee = minInsuredFee;
	}

	public String getAreaFeeType() {
		return areaFeeType;
	}

	public void setAreaFeeType(String areaFeeType) {
		this.areaFeeType = areaFeeType;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
