package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Goodscategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
	/**
	 * 类别名称
	 **/
    private String category;
	/**
	 * 父ID
	 **/
    private String parentId;

	/**
	 * 排序
	 **/
    private Integer sort;
	//修改时间
    private Date lastUpdate;
	//创建时间
    private Date createTime;
	/**
	 * 父名称
	 **/
	private String parentName;

    private List<Goodscategory> nodes;
    
    
    
	public List<Goodscategory> getNodes() {
		return nodes;
	}
	public void setNodes(List<Goodscategory> nodes) {
		this.nodes = nodes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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


	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
