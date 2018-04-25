package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;

public class OrderProduct implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;
	/**
	 * FK，商品规格ID
	 **/
	private String productId;
	private Double sellingPrice;
	private Double unitPrice;
	/**
	 * 购买数量
	 **/
	private Integer num;
	private Double discount;
	/**
	 * 成交价格
	 **/
	private Double dealPrice;
	/**
	 * 商品名称
	 **/
	private String name;
	/**
	 * 商品分类
	 **/
	private String categoryId;
	/**
	 * 分类名称
	 **/
	private String category;
	private Date lastUpdate;
	private Date createTime;
	/**
	 * 商品重量，单位：克
	 **/
	private Double weight;
	private ProductBO productBO;

	private Good goodsBO;
	/**
	 * 规格信息
	 **/
	private String specInfo;
	/**
	 * 商品原价
	 **/
	private Double goodsPrice;

	/**
	 * 是否可以退货
	 **/
	private Integer isReturn;

	private String goodsId;

	private String tradingChannels;

	private String browseUrl;

	private String imageUrl;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(Double dealPrice) {
		this.dealPrice = dealPrice;
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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(ProductBO productBO) {
		this.productBO = productBO;
	}

	public Good getGoodsBO() {
		return goodsBO;
	}

	public void setGoodsBO(Good goodsBO) {
		this.goodsBO = goodsBO;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getTradingChannels() {
		return tradingChannels;
	}

	public void setTradingChannels(String tradingChannels) {
		this.tradingChannels = tradingChannels;
	}

	public String getBrowseUrl() {
		return browseUrl;
	}

	public void setBrowseUrl(String browseUrl) {
		this.browseUrl = browseUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
