package com.abc.soa.response.cszj.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by relic5 on 2017/6/19.
 */
public class RepackBO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // （主键）
    private String id;
    // 红包口令
    private String secret;
    // 口令产生时间
    private String createTime;
    // 红包活动编号（主键）
    private String activityId;
    // 活动起始时间
    private String startTime;
    // 活动截止时间
    private String endTime;

    private Double minAmount;//随机金额中最小金额
    // 红包金额
    private Double amount;
    // 红包发放模式（固定金额、随机金额）
    private String amountType;
    // 中奖概率（1%-100%）
    private String probability;
    // 红包中奖金额
    private Double sendAmount;
    // 发送状态 0:已中奖未发送
    private String sendStatus;
    /*// 发送次数
    private Integer sendTimes;*/
    // 发送时间
    private Date sendTime;
    // 领取状态 0:已中奖未领取 1.已领取 2.未中奖
    private String receiveStatus;
    // 用户输入口令的时间
    private String receiveTime;
    // 用户IP
    private String ip;
    // 用户OPENID
    private String openId;
    //活动名称
    private String name;

    private String remark;

    private String wxnickname;

    private String phone;

    private String businessId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public Double getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(Double sendAmount) {
        this.sendAmount = sendAmount;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWxnickname() {
        return wxnickname;
    }

    public void setWxnickname(String wxnickname) {
        this.wxnickname = wxnickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}
