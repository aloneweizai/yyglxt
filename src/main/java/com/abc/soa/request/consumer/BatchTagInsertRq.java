package com.abc.soa.request.consumer;

import java.io.Serializable;

public class BatchTagInsertRq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  tagIds;
    private String subjectIds;
    
	public String getTagIds() {
		return tagIds;
	}
	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}
	public String getSubjectIds() {
		return subjectIds;
	}
	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}
    
    
}
