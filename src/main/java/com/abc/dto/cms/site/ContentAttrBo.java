package com.abc.dto.cms.site;
import java.io.Serializable;


/**
 *
 * CMS内容扩展属性表
 * add by xieyanmao on 2017-4-25
 *
 **/
@SuppressWarnings("serial")
public class ContentAttrBo implements Serializable {

	/**contentId**varchar(64)**/
	private String contentId;

	/**名称**varchar(30)**/
	private String attrName;

	/**值**varchar(255)**/
	private String attrValue;



	public void setContentId(String contentId){
		this.contentId = contentId;
	}

	public String getContentId(){
		return this.contentId;
	}

	public void setAttrName(String attrName){
		this.attrName = attrName;
	}

	public String getAttrName(){
		return this.attrName;
	}

	public void setAttrValue(String attrValue){
		this.attrValue = attrValue;
	}

	public String getAttrValue(){
		return this.attrValue;
	}

}
