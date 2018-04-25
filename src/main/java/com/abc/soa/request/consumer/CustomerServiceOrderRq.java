package com.abc.soa.request.consumer;

/**
 * @Author liuQi
 * @Date 2017/10/10 17:25
 */
public class CustomerServiceOrderRq {

    /*税号*/
    private String nsrsbh;

    /*姓名*/
    private String name;

    /*手机*/
    private String phone;

    /*时间*/
    private String date;

    private Integer page;

    private Integer size;

    public String getNsrsbh() {
        return nsrsbh;
    }

    public CustomerServiceOrderRq setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerServiceOrderRq setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerServiceOrderRq setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getDate() {
        return date;
    }

    public CustomerServiceOrderRq setDate(String date) {
        this.date = date;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public CustomerServiceOrderRq setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public CustomerServiceOrderRq setSize(Integer size) {
        this.size = size;
        return this;
    }
}
