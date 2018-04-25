package com.abc.soa.response.bangbang;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2017/10/31 17:41
 * 帮派成员奖励列表
 */
public class QuestionFactionMemberReward {

    private String id;

    private String factionId;

    /*帮派名称*/
    private String factionName;

    /*成员名称*/
    private String memberName;

    /*成员等级*/
    private String memberLevel;

    /*分配积分*/
    private String integral;

    /*审核时间*/
    private Date updateTime;

    /*审核状态*/
    private String status;

    /*创建时间*/
    private Date createTime;

    /*拒绝理由*/
    private String refuseReason;


    public String getId() {
        return id;
    }

    public QuestionFactionMemberReward setId(String id) {
        this.id = id;
        return this;
    }

    public String getFactionId() {
        return factionId;
    }

    public QuestionFactionMemberReward setFactionId(String factionId) {
        this.factionId = factionId;
        return this;
    }

    public String getFactionName() {
        return factionName;
    }

    public QuestionFactionMemberReward setFactionName(String factionName) {
        this.factionName = factionName;
        return this;
    }

    public String getMemberName() {
        return memberName;
    }

    public QuestionFactionMemberReward setMemberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public QuestionFactionMemberReward setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
        return this;
    }

    public String getIntegral() {
        return integral;
    }

    public QuestionFactionMemberReward setIntegral(String integral) {
        this.integral = integral;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public QuestionFactionMemberReward setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public QuestionFactionMemberReward setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public QuestionFactionMemberReward setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public QuestionFactionMemberReward setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
        return this;
    }
}
