package com.abc.dto.cms.site;
import java.io.Serializable;


/**
 *
 * CMS内容图片表
 * add by xieyanmao on 2017-4-25
 *
 **/
@SuppressWarnings("serial")
public class ContentPictureBo implements Serializable {

	/****/
	private String contentId;

	/**排列顺序**/
	private Integer priority;

	/**图片地址**/
	private String imgPath;

	/**描述**/
	private String description;



	public void setContentId(String contentId){
		this.contentId = contentId;
	}

	public String getContentId(){
		return this.contentId;
	}

	public void setPriority(Integer priority){
		this.priority = priority;
	}

	public Integer getPriority(){
		return this.priority;
	}

	public void setImgPath(String imgPath){
		this.imgPath = imgPath;
	}

	public String getImgPath(){
		return this.imgPath;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

}
