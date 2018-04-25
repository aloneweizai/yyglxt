package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class OrderCountBO implements Serializable {
    /**
     * 待付款
     **/
    private String orderStatus2;
    /**
     * ID
     **/
    private String orderStatus3;

    /**
     * 访问接口地址
     **/
    private String orderStatus4;
    /**
     * 访问接口地址
     **/
    private String orderStatus6;

    /**
     * 用户代理
     **/
    private String orderStatus7;

    /**
     * 接入userId
     **/
    private String orderStatus9;

    /**
     * 接入AppId
     **/
    private String orderStatus;


    public String getorderStatus3() {
        return orderStatus3;
    }

    public void setorderStatus3(String orderStatus3) {
        this.orderStatus3 = orderStatus3;
    }

    public String getorderStatus4() {
        return orderStatus4;
    }

    public void setorderStatus4(String orderStatus4) {
        this.orderStatus4 = orderStatus4;
    }

    public String getorderStatus6() {
        return orderStatus6;
    }

    public void setorderStatus6(String orderStatus6) {
        this.orderStatus6 = orderStatus6;
    }

    public String getorderStatus7() {
        return orderStatus7;
    }

    public void setorderStatus7(String orderStatus7) {
        this.orderStatus7 = orderStatus7;
    }

    public String getorderStatus9() {
        return orderStatus9;
    }

    public void setorderStatus9(String orderStatus9) {
        this.orderStatus9 = orderStatus9;
    }

    public String getorderStatus() {
        return orderStatus;
    }

    public void setorderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus2() {
        return orderStatus2;
    }

    public void setOrderStatus2(String orderStatus2) {
        this.orderStatus2 = orderStatus2;
    }
}
