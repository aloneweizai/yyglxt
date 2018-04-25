package com.abc.soa.response.financed.bo;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;

public class Good extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String imageUrl;
    private String introduction;
    private String details;
    private String categoryId;
    private String status;
    private Integer giftPoints;
    private String unit;
    private String recommendType;
    private Integer sort;
    private String tradeMethod;
    private String isShipping;
    private String isFreeShipping;
    private String goodsType;
    private String invoiceContent;
    private List<ProductBO> productBOList;
	/**是否可退货，0，可退，1，不可退**/
	private String isReturn;

	/**是否可换货，0：可换，1：不可换**/
	private String isExchange;
	private String memberLevel;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getGiftPoints() {
		return giftPoints;
	}
	public void setGiftPoints(Integer giftPoints) {
		this.giftPoints = giftPoints;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRecommendType() {
		return recommendType;
	}
	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getTradeMethod() {
		return tradeMethod;
	}
	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}
	public String getIsShipping() {
		return isShipping;
	}
	public void setIsShipping(String isShipping) {
		this.isShipping = isShipping;
	}
	public String getIsFreeShipping() {
		return isFreeShipping;
	}
	public void setIsFreeShipping(String isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}
	public List<ProductBO> getProductBOList() {
		return productBOList;
	}
	public void setProductBOList(List<ProductBO> productBOList) {
		this.productBOList = productBOList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	public String getInvoiceContent() {
		return invoiceContent;
	}
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}


	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public String getIsExchange() {
		return isExchange;
	}

	public void setIsExchange(String isExchange) {
		this.isExchange = isExchange;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
}
