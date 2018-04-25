package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.List;


/**
 * 交易记录
 **/
@SuppressWarnings("serial")
public class TradeBO implements Serializable {
    /**
     * 订单号
     **/
    private String orderNo;
    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;


    private String tradeNo;

    private List<TradeLog> tradeLogBOList;

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(java.util.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public List<TradeLog> getTradeLogBOList() {
        return tradeLogBOList;
    }

    public void setTradeLogBOList(List<TradeLog> tradeLogBOList) {
        this.tradeLogBOList = tradeLogBOList;
    }
}
