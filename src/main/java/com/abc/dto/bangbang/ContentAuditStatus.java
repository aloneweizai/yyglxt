package com.abc.dto.bangbang;

/**
 * @Author liuQi
 * @Date 2017/10/17 17:51
 */
public enum ContentAuditStatus {

    valid("0","审核通过"),
    audit("1","待审核"),
    invalid("2","拉黑");

    private String code;

    private String description;

    ContentAuditStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public String getCode(){
        return this.code;
    }

}
