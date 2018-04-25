package com.abc.soa.response.consumer.bo;

import java.util.Date;

import com.abc.common.soa.response.BaseResponse;

public class VipPrivilege extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;//特权名称
    private String icon;//icon
    private String description;//描述
    private Boolean status;//数据状态
    private Date lastUpdate;
    private Date createTime;
    private Integer sort;
	private String code;//特权代码
    
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}


	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}
}
