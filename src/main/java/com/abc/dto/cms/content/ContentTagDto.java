package com.abc.dto.cms.content;

/**
 * 内容标签
 * Created by Administrator on 2017/7/26 0026.
 */
public class ContentTagDto {

    private String contentId = "";

    private String tagId;

    private int priority;

    public ContentTagDto() {
    }

    public ContentTagDto(String contentId, String tagId, int priority) {
        this.contentId = contentId;
        this.tagId = tagId;
        this.priority = priority;
    }

    public ContentTagDto(String tagId, int priority) {
        this.tagId = tagId;
        this.priority = priority;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
