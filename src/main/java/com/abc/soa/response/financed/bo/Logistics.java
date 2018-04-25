package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;

public class Logistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
	//物流公司代码
    private String compCode;
	//物流公司名称
    private String compName;
	//网址
    private String compUrl;
	//排序
    private Integer sort;
	//修改时间
    private Date lastUpdate;
	//创建时间
    private Date createTime;
	/**模版下载地址**/
	private String templateUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompUrl() {
		return compUrl;
	}
	public void setCompUrl(String compUrl) {
		this.compUrl = compUrl;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
}
