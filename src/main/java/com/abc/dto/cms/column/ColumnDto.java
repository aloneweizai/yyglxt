package com.abc.dto.cms.column;
import java.io.Serializable;


/**
 * 
 * CMS栏目表
 * 
 **/
@SuppressWarnings("serial")
public class ColumnDto implements Serializable {

	/**channelId**varchar(64)**/
	private String channelId;

	/**模型ID**varchar(64)**/
	private String modelId;

	/**站点ID**varchar(64)**/
	private String siteId;

	/**父栏目ID**varchar(64)**/
	private String parentId;

	/**访问路径**varchar(30)**/
	private String channelPath;

	/**排列顺序**int(11)**/
	private Integer priority;

	/**是否显示**tinyint(1)**/
	private Integer isDisplay;

	/**栏目名称**varchar(100)**/
	private String channelName;

	/** 栏目标签 **/
	private String contentType;

	private String cnt;
	//站点访问路径
	private String sitePath;
	/**站点名称**/
	private String domain;
	/**栏目链接**/
	private String link;

	private String titleImg;

	public void setChannelId(String channelId){
		this.channelId = channelId;
	}

	public String getChannelId(){
		return this.channelId;
	}

	public void setModelId(String modelId){
		this.modelId = modelId;
	}

	public String getModelId(){
		return this.modelId;
	}

	public void setSiteId(String siteId){
		this.siteId = siteId;
	}

	public String getSiteId(){
		return this.siteId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getParentId(){
		return this.parentId;
	}

	public void setChannelPath(String channelPath){
		this.channelPath = channelPath;
	}

	public String getChannelPath(){
		return this.channelPath;
	}

	public void setPriority(Integer priority){
		this.priority = priority;
	}

	public Integer getPriority(){
		return this.priority;
	}

	public void setIsDisplay(Integer isDisplay){
		this.isDisplay = isDisplay;
	}

	public Integer getIsDisplay(){
		return this.isDisplay;
	}

	public void setChannelName(String channelName){
		this.channelName = channelName;
	}

	public String getChannelName(){
		return this.channelName;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSitePath() {
		return sitePath;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
}
