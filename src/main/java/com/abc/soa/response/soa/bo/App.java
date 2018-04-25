package com.abc.soa.response.soa.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * APP实体类
 * @author 2017-11-6
 *
 */
public class App implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id; //主键
    private String name; //名称
    private String nickname; //昵称
    private String startT; //开始时间字符
    private Date startTime;//开始时间
    private String endT; //结束时间字符
    private Date endTime; //结束时间
    private Boolean status; //状态
    private Date createTime; //创建时间
    private Date lastUpdate; //跟新时间
    private String remark; //备注
    /**授权应用密码**/
	private String password;
    private String oldPassWord;
	/**访问授权码**/
	private String accessToken;

	/**上次重置授权码时间**/
	private java.util.Date lastResetTokenTime;
    
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public java.util.Date getLastResetTokenTime() {
		return lastResetTokenTime;
	}
	public void setLastResetTokenTime(java.util.Date lastResetTokenTime) {
		this.lastResetTokenTime = lastResetTokenTime;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOldPassWord() {
		return oldPassWord;
	}
	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}
    
    
}
