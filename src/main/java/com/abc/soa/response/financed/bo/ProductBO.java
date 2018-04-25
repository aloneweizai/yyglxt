package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.abc.soa.response.system.Dict;

public class ProductBO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String goodsId;
	private String dictId;
	private Double marketPrice;
	private Double sellingPrice;
	private Double costPrice;
	private Double finalPrice;
	private Integer discount;
	private String uvipLevel;
	private Double weight;
	private List<Dict> dictList;
	private List<UvipPrice>  uvipPriceList;
	private String goodsName;
	private Integer startRepo;
	private Integer endRepo;
	private Date createTime;
    private Date lastUpdate;
	private Integer stock;
    
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getStartRepo() {
		return startRepo;
	}
	public void setStartRepo(Integer startRepo) {
		this.startRepo = startRepo;
	}
	public Integer getEndRepo() {
		return endRepo;
	}
	public void setEndRepo(Integer endRepo) {
		this.endRepo = endRepo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public String getUvipLevel() {
		return uvipLevel;
	}
	public void setUvipLevel(String uvipLevel) {
		this.uvipLevel = uvipLevel;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public List<Dict> getDictList() {
		return dictList;
	}
	public void setDictList(List<Dict> dictList) {
		this.dictList = dictList;
	}
	public List<UvipPrice> getUvipPriceList() {
		return uvipPriceList;
	}
	public void setUvipPriceList(List<UvipPrice> uvipPriceList) {
		this.uvipPriceList = uvipPriceList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}


	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
