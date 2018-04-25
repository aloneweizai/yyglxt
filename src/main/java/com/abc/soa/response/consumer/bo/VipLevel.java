package com.abc.soa.response.consumer.bo;

import java.util.Date;

import com.abc.common.soa.response.BaseResponse;

public class VipLevel extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8279713151023586445L;
    private String id;//会员iD
    private String name;//会员名称
    private String level;//会员等级
    private String levelCode;//等级代码
    private Double factor;//成长因子
    private boolean status;//状态
    private Date lastUpdate;//更新时间
    private Date createTime;//穿件时间
    private Double costPrice;//成本价格
    private Double marketPrice;//市场价格
    private Double salePrice;//销售价格
    private Double sendPoints;//增送积分
    private String imgUrl;//图片地址
    private long pointsPrice;//兑换积分
    public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getSendPoints() {
		return sendPoints;
	}
	public void setSendPoints(Double sendPoints) {
		this.sendPoints = sendPoints;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
    
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(Double factor) {
		this.factor = factor;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
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
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public long getPointsPrice() {
		return pointsPrice;
	}
	public void setPointsPrice(long pointsPrice) {
		this.pointsPrice = pointsPrice;
	}

    
    
}
