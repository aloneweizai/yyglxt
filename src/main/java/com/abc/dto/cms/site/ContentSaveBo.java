package com.abc.dto.cms.site;

import java.io.Serializable;
import java.util.List;


/**
 *
 * CMS内容表
 * add by xieyanmao on 2017-4-27
 *
 **/
@SuppressWarnings("serial")
public class ContentSaveBo implements Serializable {
	private ContentBo content;//内容
	private ContentExtBo contentExt;//内容扩展
	private ContentTxtBo contentTxt;//内容文本
	private List<ContentAttrBo> contentAttrList;//内容扩展属性
	private List<ContentPictureBo> contentPictureList;//内容图片
	private List<FileBo> fileList;//内容附件


	/**专题ID**/
	private String topicId;

	/**0 新增 1修改 2审核 3退回 4移动 5生成静态页 6删除到回收站 7归档 8出档 9推送共享**/
	private Integer operateType;

	public ContentBo getContent() {
		return content;
	}

	public void setContent(ContentBo content) {
		this.content = content;
	}

	public ContentExtBo getContentExt() {
		return contentExt;
	}

	public void setContentExt(ContentExtBo contentExt) {
		this.contentExt = contentExt;
	}

	public ContentTxtBo getContentTxt() {
		return contentTxt;
	}

	public void setContentTxt(ContentTxtBo contentTxt) {
		this.contentTxt = contentTxt;
	}

	public List<ContentAttrBo> getContentAttrList() {
		return contentAttrList;
	}

	public void setContentAttrList(List<ContentAttrBo> contentAttrList) {
		this.contentAttrList = contentAttrList;
	}

	public List<ContentPictureBo> getContentPictureList() {
		return contentPictureList;
	}

	public void setContentPictureList(List<ContentPictureBo> contentPictureList) {
		this.contentPictureList = contentPictureList;
	}

	public List<FileBo> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileBo> fileList) {
		this.fileList = fileList;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

}
