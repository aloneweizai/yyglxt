package com.abc.soa.response.soa.bo;

import java.io.Serializable;
import java.util.Date;

public class AppSetting implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String appId;
    private String apiId;
    private String timesPerMinute;
    private String timesPerHour;
    private String timesPerDay;
    private String name;
    private String uri;
    private String method;
    private String version;
    private boolean authentication;
    private boolean status;
    private Date createTime;
    private Date lastUpdate;
    private String dictId;
    private boolean isValidate;
    private String appName;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getTimesPerMinute() {
		return timesPerMinute;
	}
	public void setTimesPerMinute(String timesPerMinute) {
		this.timesPerMinute = timesPerMinute;
	}
	public String getTimesPerHour() {
		return timesPerHour;
	}
	public void setTimesPerHour(String timesPerHour) {
		this.timesPerHour = timesPerHour;
	}
	public String getTimesPerDay() {
		return timesPerDay;
	}
	public void setTimesPerDay(String timesPerDay) {
		this.timesPerDay = timesPerDay;
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
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public boolean getIsValidate() {
		return isValidate;
	}
	public void setIsValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}
    
    
}
