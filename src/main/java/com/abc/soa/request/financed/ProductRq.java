package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class ProductRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String categoryId;
	private String recommendType;
	private Boolean status;
	private String goodsIds;
	private String goodsType;

	public String getGoodsType() {
		return goodsType;
	}

	public ProductRq setGoodsType(String goodsType) {
		this.goodsType = goodsType;
		return this;
	}

	public String getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
