package com.abc.soa.response.financed.bo;

import com.abc.soa.response.consumer.bo.Consumer;
import com.abc.soa.response.system.Dict;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 订单信息
 * 
 * @author zhushuai 2017-6-28
 * 
 */
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1825071923009848547L;
	private String id;
	/**
	 * 订单编号
	 **/
	private String orderNo;
	/**
	 * 用户ID
	 **/
	private String userId;
	/**
	 * 订单状态，2：待付款，3：付款中，4：付款成功，5：已发货，6：已完成，7：已结束，8：付款失败，9：已退单, 10:退换货中
	 **/
	private String orderStatus;
	/**
	 * 配送方式
	 **/
	private String deliveryMethod;
	/**
	 * 支付方式：WEIXIN、ALIPAY、POINTS
	 **/
	private String payMethod;

	private String vipLevel;
	//会员名称
	private String nowVipLevelName;
	private Date lastUpdate;
	private Date createTime;
	/**
	 * 用户名
	 **/
	private String username;
	/**
	 * 是否需要寄送
	 **/
	private Integer isShipping;
	/**
	 * 是否免运费
	 **/
	private Integer isFreeShipping;
	/**
	 * 配送费
	 **/
	private Double deliveryFee;
	private Integer isInsured;
	private Double insuredFee;
	/**
	 * 成交金额
	 **/
	private Double totalPrice;
	private String addressId;
	/**
	 * 运单号
	 **/
	private String expressNo;
	/**
	 * 备注
	 **/
	private String remark;
	//// 用户信息
	private Consumer user;
	// //订单和商品对应关系
	private List<OrderProduct> orderProductBOList;
	////字典信息
    private List<Dict> dictBOList;
	//开始时间
    private Date startTime;
	//结束时间
    private Date endTime;
	/**
	 * 当前会员等级
	 **/
    private String nowVipLevel;
	//发票信息
    private Invoice invoiceBO;
	////订单日志
	private List<OrderLog> orderLogBOList;
	private List<TradeLog> tradeLogBOList;
	private UserAddress userAddressBO;

	private String userOrderNo;
	/**
	 * 赠送积分
	 **/
    private String giftPoints;
	/**
	 * 交易方式：RMB、POINTS
	 **/
    private String tradeMethod;
	/**
	 * 推荐人工号
	 **/
    private String recommendUser;

	/**物流公司ID**/
	private String expressCompId;
	private Logistics expressComp;

	//收货地址
	private String shippingAddress;
	//收件人号码
	private String contactNumber;
	//收件人
	private String consignee;
	//交易信息
	private TradeBO tradeBO;

	//交易信息列表对象
	private List<TradeBO> tradeBOList;

	//课堂优惠赠送
	private List<OrderGiftBO> orderGiftBOList;

	private String tradingChannels;
    
	public List<Dict> getDictBOList() {
		return dictBOList;
	}

	public void setDictBOList(List<Dict> dictBOList) {
		this.dictBOList = dictBOList;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Integer getIsInsured() {
		return isInsured;
	}

	public void setIsInsured(Integer isInsured) {
		this.isInsured = isInsured;
	}

	public Double getInsuredFee() {
		return insuredFee;
	}

	public void setInsuredFee(Double insuredFee) {
		this.insuredFee = insuredFee;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Consumer getUser() {
		return user;
	}

	public void setUser(Consumer user) {
		this.user = user;
	}

	public List<OrderProduct> getOrderProductBOList() {
		return orderProductBOList;
	}

	public void setOrderProductBOList(List<OrderProduct> orderProductBOList) {
		this.orderProductBOList = orderProductBOList;
	}

	public String getNowVipLevel() {
		return nowVipLevel;
	}

	public void setNowVipLevel(String nowVipLevel) {
		this.nowVipLevel = nowVipLevel;
	}

	public Invoice getInvoiceBO() {
		return invoiceBO;
	}

	public void setInvoiceBO(Invoice invoiceBO) {
		this.invoiceBO = invoiceBO;
	}


	public List<OrderLog> getOrderLogBOList() {
		return orderLogBOList;
	}

	public void setOrderLogBOList(List<OrderLog> orderLogBOList) {
		this.orderLogBOList = orderLogBOList;
	}

	public String getUserOrderNo() {
		return userOrderNo;
	}

	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
	}

	public UserAddress getUserAddressBO() {
		return userAddressBO;
	}

	public void setUserAddressBO(UserAddress userAddressBO) {
		this.userAddressBO = userAddressBO;
	}

	public String getGiftPoints() {
		return giftPoints;
	}

	public void setGiftPoints(String giftPoints) {
		this.giftPoints = giftPoints;
	}

	public List<TradeLog> getTradeLogBOList() {
		return tradeLogBOList;
	}

	public void setTradeLogBOList(List<TradeLog> tradeLogBOList) {
		this.tradeLogBOList = tradeLogBOList;
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

	

	public String getNowVipLevelName() {
		return nowVipLevelName;
	}

	public void setNowVipLevelName(String nowVipLevelName) {
		this.nowVipLevelName = nowVipLevelName;
	}

	public String getExpressCompId() {
		return expressCompId;
	}

	public void setExpressCompId(String expressCompId) {
		this.expressCompId = expressCompId;
	}

	public Logistics getExpressComp() {
		return expressComp;
	}

	public void setExpressComp(Logistics expressComp) {
		this.expressComp = expressComp;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public TradeBO getTradeBO() {
		return tradeBO;
	}

	public void setTradeBO(TradeBO tradeBO) {
		this.tradeBO = tradeBO;
	}

	public List<TradeBO> getTradeBOList() {
		return tradeBOList;
	}

	public void setTradeBOList(List<TradeBO> tradeBOList) {
		this.tradeBOList = tradeBOList;
	}

	public List<OrderGiftBO> getOrderGiftBOList() {
		return orderGiftBOList;
	}

	public void setOrderGiftBOList(List<OrderGiftBO> orderGiftBOList) {
		this.orderGiftBOList = orderGiftBOList;
	}

	public String getTradingChannels() {
		return tradingChannels;
	}

	public void setTradingChannels(String tradingChannels) {
		this.tradingChannels = tradingChannels;
	}
}
