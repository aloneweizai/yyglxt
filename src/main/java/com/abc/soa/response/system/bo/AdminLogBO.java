package com.abc.soa.response.system.bo;

import java.util.Date;

/**
 * @author liuguiyao
 * @create 2017-04-27 10:08 AM
 * @since 1.0.0
 */
public class AdminLogBO {
    // 主键
    private String id;
    // 用户ID
    private String userId;
    // 业务ID
    private String businessUri;
    // 业务ID
    private String businessUriStr;
    // 业务名称
    private String businessName;
    // 业务数据
    private String businessData;
    // 业务数据
    private String businessDataStr;
    // 操作类型：POST（新增）、PUT（修改）、GET（查询）、DELETE（删除）
    private String method;
    // 操作时间
    private Date createTime;
    // 备注
    private String remark;

    // 开始日期，用于查询
    private Date startDate;
    // 结束日期，用于查询
    private Date endDate;
    // 用户名，用于查询
    private String username;
    // 昵称，用于查询
    private String nickname;

    private String adminId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBusinessUri() {
        return businessUri;
    }

    public void setBusinessUri(String businessUri) {
        this.businessUri = businessUri;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBusinessUriStr() {
        return businessUriStr;
    }

    public void setBusinessUriStr(String businessUriStr) {
        this.businessUriStr = businessUriStr;
    }

    public String getBusinessDataStr() {
        return businessDataStr;
    }

    public void setBusinessDataStr(String businessDataStr) {
        this.businessDataStr = businessDataStr;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}