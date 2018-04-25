package com.abc.soa.response.financed.bo;


import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 用户换货
 **/
@SuppressWarnings("serial")
public class OrderExchange implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * FK
     **/
    private String userId;

    /**
     * 订单号
     **/
    private String orderNo;

    /**
     * 换货原因
     **/
    private String reason;

    /**
     * 用户备注
     **/
    private String userRemark;

    /**
     * 管理员备注
     **/
    private String adminRemark;

    /**
     * 快递单号
     **/
    private String expressNo;

    /**
     * 快递公司
     **/
    private String expressComp;

    /**
     * 退单状态
     **/
    private String status;

    /****/
    private Timestamp createTime;

    /****/
    private Timestamp lastUpdate;
    
    
    private String toExpressNo;

    /**
     * 用户换货，寄送的快递公司ID
     **/
    private String toExpressComp;

    /**
     * 用户换货，寄送的快递公司名称
     **/
    private String toExpressCompName;
    
 // 服务类型 1-换货 2-退货
    private String type;
    // 产品名称
    private String name;
    // 产品图片
    private String imageUrl;
    // 用户名
    private String username;
    // 下单时间
    private Timestamp orderTime;
    // 商品数量
    private Integer num;
    
    private String goodsType;

    private String adminConfirmRemark;

    //收货地址
    private String shippingAddress;
    //收件人号码
    private String contactNumber;
    //收件人
    private String consignee;

    private String refundRemark;
    

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getToExpressNo() {
		return toExpressNo;
	}

	public void setToExpressNo(String toExpressNo) {
		this.toExpressNo = toExpressNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OrderExchange() {
    }

    public OrderExchange(String id, String userId, String orderNo, String reason, String userRemark, String
            adminRemark, String expressNo, String expressComp, String status, Timestamp createTime, Timestamp
            lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.orderNo = orderNo;
        this.reason = reason;
        this.userRemark = userRemark;
        this.adminRemark = adminRemark;
        this.expressNo = expressNo;
        this.expressComp = expressComp;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }   

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExpressNo() {
        return this.expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressComp() {
        return this.expressComp;
    }

    public void setExpressComp(String expressComp) {
        this.expressComp = expressComp;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}


    public String getAdminConfirmRemark() {
        return adminConfirmRemark;
    }

    public void setAdminConfirmRemark(String adminConfirmRemark) {
        this.adminConfirmRemark = adminConfirmRemark;
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

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

    public String getToExpressComp() {
        return toExpressComp;
    }

    public void setToExpressComp(String toExpressComp) {
        this.toExpressComp = toExpressComp;
    }

    public String getToExpressCompName() {
        return toExpressCompName;
    }

    public void setToExpressCompName(String toExpressCompName) {
        this.toExpressCompName = toExpressCompName;
    }
}

