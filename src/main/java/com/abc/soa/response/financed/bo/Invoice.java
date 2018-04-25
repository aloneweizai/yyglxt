package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.List;




public class Invoice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * PK
	 **/
	private String id;
	/**
	 * 用户ID
	 **/
    private String userId;
	/**
	 * 用户名
	 **/
    private String username;
	/**
	 * 发票号码
	 **/
    private String invoiceNo;
	/**
	 * 发票代码
	 **/
    private String invoiceCode;
	/**
	 * 发票抬头：1.个人 2.公司
	 **/
    private String name;
	/**
	 * 发票内容：1.软件服务费 2.财税咨询费 3.技术服务费 4.财税培训费
	 **/
    private String content;
	/**
	 * 开票公司名称
	 **/
    private String compName;

	/**
	 * 发票金额
	 **/
    private Double amount;
	/**
	 * 发票类型：1.增值税普通发票 2.增值税专用发票
	 **/
    private String type;
	/**
	 * 发票性质：1.纸质发票 2.电子发票
	 **/
    private String property;
	/**
	 * 发票状态
	 **/
    private String status;
	/**
	 * 创建时间
	 **/
    private java.util.Date createTime;
	/**
	 * 修改时间
	 **/
    private java.util.Date lastUpdate;
	/**
	 * 纳税人识别号
	 **/
    private String nsrsbh;
	/**
	 * 公司名称
	 **/
    private String nsrmc;
	/**
	 * 注册地址
	 **/
    private String address;
	/**
	 * 注册电话
	 **/
    private String phone;
	/**
	 * 开户银行
	 **/
    private String bank;
	/**
	 * 用户快递地址ID
	 **/
    private String addressId;
    /**用户订单号(运单号)**/
    private String userOrderNo;
	/**
	 * 配送方式
	 **/
    private String deliveryMethod;
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
	/**
	 * 是否保价
	 **/
    private Integer isInsured;
	/**
	 * 保价费
	 **/
    private Double InsuredFee;
	/**
	 * 支付方式：WEIXIN、ALIPAY
	 **/
    private String payMethod;
    /** 收货人**/
    private String consignee;

    private List<Order> orderBOList;
    private String orderNos;
    private java.util.Date startTime;
    private java.util.Date endTime;
    
    private UserAddress userAddressBO;
    private Shipping deliveryMethodBO;
    
    private InvoiceRepoDetail invoiceDetail;
    /**是否已开发票，true：是，false：否**/
    private Boolean isInvoice;

    private InvoiceLog invoiceLog;
    
    private Boolean isBilling;
	/**
	 * 运单号
	 */
    private String waybillNum;
	/**
	 * 邮箱
	 */
    private String email;
	/**
	 * 备注
	 */
	private String remark;
	/**联系电话**/
	private String contactNumber;
	/**收件地址**/
	private String shippingAddress;
    
    
	public InvoiceRepoDetail getInvoiceDetail() {
		return invoiceDetail;
	}
	public void setInvoiceDetail(InvoiceRepoDetail invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}
	public Boolean getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(Boolean isInvoice) {
		this.isInvoice = isInvoice;
	}
	public InvoiceLog getInvoiceLog() {
		return invoiceLog;
	}
	public void setInvoiceLog(InvoiceLog invoiceLog) {
		this.invoiceLog = invoiceLog;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(java.util.Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getUserOrderNo() {
		return userOrderNo;
	}
	public void setUserOrderNo(String userOrderNo) {
		this.userOrderNo = userOrderNo;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
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
		return InsuredFee;
	}
	public void setInsuredFee(Double insuredFee) {
		InsuredFee = insuredFee;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public List<Order> getOrderBOList() {
		return orderBOList;
	}
	public void setOrderBOList(List<Order> orderBOList) {
		this.orderBOList = orderBOList;
	}
	public String getOrderNos() {
		return orderNos;
	}
	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
	}
	public java.util.Date getStartTime() {
		return startTime;
	}
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	public java.util.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	
	public Shipping getDeliveryMethodBO() {
		return deliveryMethodBO;
	}
	public void setDeliveryMethodBO(Shipping deliveryMethodBO) {
		this.deliveryMethodBO = deliveryMethodBO;
	}
	public Boolean getIsBilling() {
		return isBilling;
	}
	public void setIsBilling(Boolean isBilling) {
		this.isBilling = isBilling;
	}
	public UserAddress getUserAddressBO() {
		return userAddressBO;
	}
	public void setUserAddressBO(UserAddress userAddressBO) {
		this.userAddressBO = userAddressBO;
	}
	public String getWaybillNum() {
		return waybillNum;
	}
	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
