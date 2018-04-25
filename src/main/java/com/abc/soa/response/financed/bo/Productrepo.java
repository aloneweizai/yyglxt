package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;


public class Productrepo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
    private String goodsId;
    private String productId;  
    private Integer income;
    private Integer outcome;
    private Integer stock;
    private String remark;
    private String optionType;
    private Date createTime;
    private Date lastUpdate;
    
    
    
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getOutcome() {
		return outcome;
	}
	public void setOutcome(Integer outcome) {
		this.outcome = outcome;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
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
    
	
    
}
