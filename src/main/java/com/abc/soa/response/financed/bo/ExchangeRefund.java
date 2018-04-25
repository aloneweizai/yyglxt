package com.abc.soa.response.financed.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 退款BO
 */
public class ExchangeRefund {

    private String id;
    // 退款金额流向
    @NotEmpty
    @Length(min = 1, max = 10)
    private String refundType;
    // 退款金额
    @NotEmpty
    private Double amount;
    // 备注
    @NotEmpty
    @Length(max = 500)
    private String refundRemark;

    private String tradeMethod;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }
}
