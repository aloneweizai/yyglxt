package com.abc.soa.response.financed;

import java.io.Serializable;

public class AutoComplete implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutoComplete(){}
	
	public AutoComplete(String title,Object object){
		this.title=title;
		this.result=object;
	}
	
	private String title;
	private Object result;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getObject() {
		return result;
	}

	public void setObject(Object object) {
		this.result = object;
	}

}
