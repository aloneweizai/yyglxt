package com.abc.dto.bangbang;

/**
 * @Author liuQi
 * @Date 2017/10/16 14:43
 */
public enum HeadmanStatus {

    apply("申请中"),
    success("通过"),
    refuse("拒绝");

    private String description;

    HeadmanStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}
