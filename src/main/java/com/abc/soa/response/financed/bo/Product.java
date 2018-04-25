package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * PK
	 **/
	private String id;
	/**
	 * 产品名称
	 **/
	private String name;
	/**
	 * 展示图片URL
	 **/
	private String imageUrl;
	/**
	 * 产品介绍
	 **/
	private String introduction;
	/**
	 * 产品详情
	 **/
	private String details;
	/**
	 * 产品分类ID
	 **/
	private String categoryId;

	private String categoryName;
	/**
	 * 是否上架：boolean
	 **/
	private Boolean status;

	private Date lastUpdate;
	private Date createTime;
	/**
	 * 排序
	 **/
	private Integer sort;
	/**
	 * 赠送积分
	 **/
	private Integer giftPoints;
	/**
	 * 计件单位显示
	 **/
	private String unit;
	/**
	 * 推荐类型：1.最新产品 2.特价产品 3.热卖产品 4.推荐产品
	 **/
	private String recommendType;
	private Double totalPrice;
	private Integer totalStock;
	/**
	 * 是否需要寄送
	 **/
	private Integer isShipping;
	/**
	 * 是否免运费
	 **/
	private Integer isFreeShipping;
	/**
	 * 交易方式：RMB、POINTS
	 **/
	private String tradeMethod;
	/**
	 * 商品类型
	 **/
	private String goodsType;
	private List<ProductBO> productBOList;
	private String sellingPrice;
	/**
	 * 发票内容：1.软件服务费 2.财税咨询费 3.技术服务费 4.财税培训费
	 *
	 * @return
	 */
	private String invoiceContent;
	/**
	 * 是否可退货，0，可退，1，不可退
	 **/
	private String isReturn;

	/**是否可换货，0：可换，1：不可换**/
	private String isExchange;
	/**
	 * 会员等级
	 **/
	private String memberLevel;
	public Integer getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(Integer isShipping) {
		this.isShipping = isShipping;
	}

	public Integer getIsFreeShipping() {
		return isFreeShipping;
	}

	public void setIsFreeShipping(Integer isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}

	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public List<ProductBO> getProductBOList() {
		return productBOList;
	}

	public void setProductBOList(List<ProductBO> productBOList) {
		this.productBOList = productBOList;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
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
