package com.abc.soa.response.financed.bo;

public class SfImportBO {

    private String orderNo;
    private String expressNo;

    private String expressComp;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    @Override
    public String toString() {
        return "SfImportBO{" +
                "orderNo='" + orderNo + '\'' +
                ", expressNo='" + expressNo + '\'' +
                '}';
    }

    public String getExpressComp() {
        return expressComp;
    }

    public void setExpressComp(String expressComp) {
        this.expressComp = expressComp;
    }
}
