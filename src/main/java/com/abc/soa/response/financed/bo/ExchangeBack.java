package com.abc.soa.response.financed.bo;

import org.hibernate.validator.constraints.Length;


public class ExchangeBack {

    private String id;
    /**
     * 用户换货快递单号
     **/
    @Length(min=6, max = 32)
    private String expressNo;

    /**
     * 用户换货快递公司
     **/
    @Length(min=1, max = 100)
    private String expressComp;

    //审核员备注
    @Length(max = 500)
    private String adminRemark;
    /**
     * 管理员确认备注
     */
    private String adminConfirmRemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressComp() {
        return expressComp;
    }

    public void setExpressComp(String expressComp) {
        this.expressComp = expressComp;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }

    @Override
    public String toString() {
        return "ExchangeAdminBO{" +
                "id='" + id + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", expressComp='" + expressComp + '\'' +
                ", adminRemark='" + adminRemark + '\'' +
                '}';
    }

    public String getAdminConfirmRemark() {
        return adminConfirmRemark;
    }

    public void setAdminConfirmRemark(String adminConfirmRemark) {
        this.adminConfirmRemark = adminConfirmRemark;
    }
}
