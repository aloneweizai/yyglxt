package com.abc.dto.bangbang;

/**
 * @Author liuQi
 * @Date 2017/10/18 14:17
 */
public enum TipOffAuditStatus {

    auditing("待审核"),
    approved("审核通过"),
    refuse("审核拒绝");

    private String description;

    TipOffAuditStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
