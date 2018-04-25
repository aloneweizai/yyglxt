package com.abc.soa.response.financed.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author zhushuai 2017-8-14
 *
 */
public class TradeBill {

    @NotEmpty
    @Length(min = 16, max = 64)
    private String orderNo;

    @NotEmpty
    @Length(min = 16, max = 64)
    private String tradeNo;

    @NotEmpty
    private Double amount;

    //阿里交易流水
    private String aliTrandeNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAliTrandeNo() {
        return aliTrandeNo;
    }

    public void setAliTrandeNo(String aliTrandeNo) {
        this.aliTrandeNo = aliTrandeNo;
    }
}
