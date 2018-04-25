package com.abc.soa.response.financed.bo;

import com.abc.common.soa.response.BaseResponse;

import java.util.Date;

public class OrderReturnBO extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String source;
    private String levelId;
    private String level;
    private Date createTime;
    private String vipExpireDate;
	private String orderNo;
	private String goodsId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getVipExpireDate() {
		return vipExpireDate;
	}

	public void setVipExpireDate(String vipExpireDate) {
		this.vipExpireDate = vipExpireDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
}
