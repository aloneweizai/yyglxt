package com.abc.dto.cms.content;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by relic5 on 2017/6/11.
 */
public class ContentInsertOrUpdateDto {

    private ContentDto content;

    private ContentExtDto contentExt;

    private ContentTxtDto contentTxt;

    private List<ContentAttrDto> contentAttrList;

    private List<ContentPictureDto> contentPictureList;

    private List<ContentFileDto> fileList;

    private List<ContentGroupViewDto> groupList;

    private List<ContentTagDto> tagList;

    public ContentDto getContent() {
        return content;
    }

    public void setContent(ContentDto content) {
        this.content = content;
    }

    public ContentExtDto getContentExt() {
        return contentExt;
    }

    public void setContentExt(ContentExtDto contentExt) {
        this.contentExt = contentExt;
    }

    public ContentTxtDto getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(ContentTxtDto contentTxt) {
        this.contentTxt = contentTxt;
    }

    public List<ContentAttrDto> getContentAttrList() {
        return contentAttrList;
    }

    public void setContentAttrList(List<ContentAttrDto> contentAttrList) {
        this.contentAttrList = contentAttrList;
    }

    public List<ContentPictureDto> getContentPictureList() {
        return contentPictureList;
    }

    public void setContentPictureList(List<ContentPictureDto> contentPictureList) {
        this.contentPictureList = contentPictureList;
    }

    public List<ContentFileDto> getFileList() {
        return fileList;
    }

    public void setFileList(List<ContentFileDto> fileList) {
        this.fileList = fileList;
    }

    public List<ContentGroupViewDto> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<ContentGroupViewDto> groupList) {
        this.groupList = groupList;
    }

    public List<ContentTagDto> getTagList() {
        return tagList;
    }

    public void setTagList(List<ContentTagDto> tagList) {
        this.tagList = tagList;
    }
}
