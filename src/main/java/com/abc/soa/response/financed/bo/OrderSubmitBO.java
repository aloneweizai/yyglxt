package com.abc.soa.response.financed.bo;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

public class OrderSubmitBO extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 **/
	private String userId;

	/**
	 * 配送方式
	 **/
	private String deliveryMethod;

	/**
	 * 当前会员等级
	 **/
	private String nowVipLevel;

	/**
	 * 用户名
	 **/
	private String username;

	/**
	 * 配送费
	 **/
	private Double deliveryFee;

	/**
	 * 成交金额
	 **/
	private Double totalPrice;


	/**
	 * 备注
	 **/
	private String remark;

	/**
	 * 赠送积分
	 **/
	private Integer giftPoints;

	/**
	 * 交易方式：RMB、POINTS
	 **/
	private String tradeMethod;

	/**
	 * 推荐人工号
	 **/
	private String recommendUser;

	/**
	 * 收件人
	 **/

	private String consignee;

	/**
	 * 联系电话
	 */
	private String contactNumber;

	/**
	 * 收件地址
	 **/
	private String shippingAddress;

	/**
	 * 是否需要寄送
	 **/
	private Integer isShipping;

	/**
	 * 是否免运费
	 **/
	private Integer isFreeShipping;

	/**
	 * 领用的优惠劵ID
	 */
	private String useCouponId;

	//订单和商品对应关系
	private List<OrderProduct> orderProductBOList;

	//课堂优惠赠送
	private List<OrderGiftBO> orderGiftBOList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getNowVipLevel() {
		return nowVipLevel;
	}

	public void setNowVipLevel(String nowVipLevel) {
		this.nowVipLevel = nowVipLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGiftPoints() {
		return giftPoints;
	}

	public void setGiftPoints(Integer giftPoints) {
		this.giftPoints = giftPoints;
	}

	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public String getRecommendUser() {
		return recommendUser;
	}

	public void setRecommendUser(String recommendUser) {
		this.recommendUser = recommendUser;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

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

	public String getUseCouponId() {
		return useCouponId;
	}

	public void setUseCouponId(String useCouponId) {
		this.useCouponId = useCouponId;
	}

	public List<OrderGiftBO> getOrderGiftBOList() {
		return orderGiftBOList;
	}

	public void setOrderGiftBOList(List<OrderGiftBO> orderGiftBOList) {
		this.orderGiftBOList = orderGiftBOList;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<OrderProduct> getOrderProductBOList() {
		return orderProductBOList;
	}

	public void setOrderProductBOList(List<OrderProduct> orderProductBOList) {
		this.orderProductBOList = orderProductBOList;
	}
}
