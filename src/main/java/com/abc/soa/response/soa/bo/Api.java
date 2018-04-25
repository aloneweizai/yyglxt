package com.abc.soa.response.soa.bo;

import java.io.Serializable;
import java.util.Date;
/**
 * APi实体类
 * @author  2017-11-6
 *
 */
public class Api implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	//接口名称
    private String name;
    //接口地址
    private String uri;
    // 接口方法
    private String method;
    // 版本
    private String version;
    // 接口所属系统
    private String appId;
    private String appName;
    // 是否需要验证用户身份
    private boolean authentication;
    // 接口状态
    private boolean status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastUpdate;
    //字典名称
    private String dictName;
    //字典ID
    private String dictId;
    
    
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
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
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public boolean isAuthentication() {
		return authentication;
	}
	public void setAuthentication(boolean authentication) {
		this.authentication = authentication;
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
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
    
    
    
}
