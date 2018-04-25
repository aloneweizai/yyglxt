package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class GoodscategoryRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -363936191767928505L;
	//商品分类名称
    private String category;
	////商品分类id
    private String id;
    
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
}
