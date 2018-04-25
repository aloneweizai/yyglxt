package com.abc.soa.response.consumer.bo;


import java.util.Date;

import com.abc.common.soa.response.BaseResponse;

public class Tag extends BaseResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String tagName;
    private boolean status;
    private Date createTime;
    private Date lastUpdate;
    private String category;
    private String description;
    private String rule;
    private Integer tagedCount;
    private String weight;
    private String type;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Integer getTagedCount() {
		return tagedCount;
	}
	public void setTagedCount(Integer tagedCount) {
		this.tagedCount = tagedCount;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    
}
