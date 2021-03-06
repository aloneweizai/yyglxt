package com.abc.dto.cms.column;
import java.io.Serializable;


/**
 * 
 * CMS栏目内容表
 * 
 **/
@SuppressWarnings("serial")
public class ColumnExtDto implements Serializable {

	/**channelId**varchar(64)**/
	private String channelId;

	/**外部链接**varchar(255)**/
	private String link;

	/**栏目页模板**varchar(100)**/
	private String tplChannel;

	/**栏目列表模板**varchar(100)**/
	private String tplContent;

	/**标题图**varchar(100)**/
	private String titleImg;

	/**内容图**varchar(100)**/
	private String contentImg;

	/**评论(0:匿名;1:会员一次;2:关闭,3会员多次)**int(11)**/
	private Integer commentControl;

	private String title;

	/**meta关键字**varchar(255)**/
	private String keywords;

	/**meta描述**varchar(255)**/
	private String description;

	private String txt;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTplChannel() {
		return tplChannel;
	}

	public void setTplChannel(String tplChannel) {
		this.tplChannel = tplChannel;
	}

	public String getTplContent() {
		return tplContent;
	}

	public void setTplContent(String tplContent) {
		this.tplContent = tplContent;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getContentImg() {
		return contentImg;
	}

	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}

	public Integer getCommentControl() {
		return commentControl;
	}

	public void setCommentControl(Integer commentControl) {
		this.commentControl = commentControl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
}
