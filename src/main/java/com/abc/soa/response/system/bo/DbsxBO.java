package com.abc.soa.response.system.bo;


import java.io.Serializable;



/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class DbsxBO implements Serializable {

    /**
     * ID
     **/
    private String num1;

    /**
     * 访问接口地址
     **/
    private String num2;
    /**
     * 访问接口地址
     **/
    private String num3;

    /**
     * 用户代理
     **/
    private String num4;

    /**
     * 接入userId
     **/
    private String num5;

    /**
     * 接入AppId
     **/
    private String num6;
    private String num7;

    //本月注册数统计
    private String regsMonth;
    //本月纳税人登录数统计
    private String nsrLoginsMonth;
    //今天纳税人登录电子申报数
    private String dzsbLoginsDay;
    //今天用户注册数
    private String regsDay;

    private String giftCount;

    private String giftSend;

    public String getRegsMonth() {
        return regsMonth;
    }

    public void setRegsMonth(String regsMonth) {
        this.regsMonth = regsMonth;
    }

    public String getNsrLoginsMonth() {
        return nsrLoginsMonth;
    }

    public void setNsrLoginsMonth(String nsrLoginsMonth) {
        this.nsrLoginsMonth = nsrLoginsMonth;
    }

    public String getDzsbLoginsDay() {
        return dzsbLoginsDay;
    }

    public void setDzsbLoginsDay(String dzsbLoginsDay) {
        this.dzsbLoginsDay = dzsbLoginsDay;
    }

    public String getRegsDay() {
        return regsDay;
    }

    public void setRegsDay(String regsDay) {
        this.regsDay = regsDay;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getNum4() {
        return num4;
    }

    public void setNum4(String num4) {
        this.num4 = num4;
    }

    public String getNum5() {
        return num5;
    }

    public void setNum5(String num5) {
        this.num5 = num5;
    }

    public String getNum6() {
        return num6;
    }

    public void setNum6(String num6) {
        this.num6 = num6;
    }

    public String getNum7() {
        return num7;
    }

    public DbsxBO setNum7(String num7) {
        this.num7 = num7;
        return this;
    }

    public String getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(String giftCount) {
        this.giftCount = giftCount;
    }

    public String getGiftSend() {
        return giftSend;
    }

    public void setGiftSend(String giftSend) {
        this.giftSend = giftSend;
    }
}
