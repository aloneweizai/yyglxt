package com.abc.soa.response.cszj.bo;

import java.io.Serializable;

/**
 * Created by relic5 on 2017/6/19.
 */
public class WxActivityBO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 活动编号（系统产生）
    private String id;
    // 活动名称
    private String name;
    // 活动描述
    private String description;
    // 活动起始时间
    private String startTime;
    // 活动截止时间
    private String endTime;
    // 规则类型
    private String ruleType;
    // 生成规则定义
    private String rule;
    // 固定金额／随机金额
    private String amountType;
    /**
     * 随机金额-最小金额
     */
    private Double minAmount;
    // 随机最大金额/固定金额
    private Double amount;
    // 中奖概率（1%-100%）
    private String probability;
    // 活动启动/停止状态（启动状态时平台才产生口令）
    private boolean status;
    // 红包祝福语
    private String wishing;
    // 红包备注
    private String remark;
    // 红包数量
    private Integer num;
    // 每天抽奖次数限制
    private Integer times;
    // 创建时间
    private String createTime;
    // 修改时间
    private String lastUpString;

    // 已发送人数
    private Integer sent;

    // 已发送金额
    private Double sentAmount;

    // 已领取人数
    private Integer received;

    // 已领取金额
    private Double receivedAmount;
    // 活动参与人数
    private Integer nop;
    //是否过期
    private boolean isChecked;

    private Integer sort;

    private Boolean outdated;


    /**
     * 是否赠送积分
     */
    private Boolean giftPoints;

    private Integer points;

    /**
     * 是否赠送优惠劵
     */
    private Boolean giftCoupon;

    /**
     * 优惠劵活动ID
     */
    private String activityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getLastUpString() {
        return lastUpString;
    }

    public void setLastUpString(String lastUpString) {
        this.lastUpString = lastUpString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getSent() {
        return sent;
    }

    public void setSent(Integer sent) {
        this.sent = sent;
    }

    public Double getSentAmount() {
        return sentAmount;
    }

    public void setSentAmount(Double sentAmount) {
        this.sentAmount = sentAmount;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Integer getNop() {
        return nop;
    }

    public void setNop(Integer nop) {
        this.nop = nop;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public Boolean getOutdated() {
		return outdated;
	}

	public void setOutdated(Boolean outdated) {
		this.outdated = outdated;
	}


    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Boolean getGiftPoints() {
        return giftPoints;
    }

    public void setGiftPoints(Boolean giftPoints) {
        this.giftPoints = giftPoints;
    }

    public Boolean getGiftCoupon() {
        return giftCoupon;
    }

    public void setGiftCoupon(Boolean giftCoupon) {
        this.giftCoupon = giftCoupon;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
