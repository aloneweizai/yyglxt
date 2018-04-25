package com.abc.soa.response.financed.bo;

/**
 * 顺丰导出对象
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-10 5:59 PM
 * @since 1.0.0
 */
public class SfExportBO {

    // 订单号
    private String orderNo;
    // 公司名称
    private String company;
    // 收件人
    private String name;
    // 手机
    private String phone;
    // 电话
    private String tel;
    // 地址
    private String address;
    // 内容
    private String content;
    // 数量
    private Integer num;

    private String cargoContent;

    public SfExportBO() {
    }

    public SfExportBO(String orderNo, String company, String name, String phone, String tel, String address, String
            content,String cargoContent, Integer num) {
        this.orderNo = orderNo;
        this.company = company;
        this.name = name;
        this.phone = phone;
        this.tel = tel;
        this.address = address;
        this.content = content;
        this.cargoContent = cargoContent;
        this.num = num;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SfExportBO{" +
                "orderNo='" + orderNo + '\'' +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", cargoContent='" + cargoContent + '\'' +
                ", num=" + num +
                '}';
    }

    public String getCargoContent() {
        return cargoContent;
    }

    public void setCargoContent(String cargoContent) {
        this.cargoContent = cargoContent;
    }
}
