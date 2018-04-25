package com.abc.dto.system;

/**
 * 组织类型
 * @Author liuqi
 * @Date 2017/5/22 10:57
 */
public enum OrgType {

    COMPANY("1","单位"),
    DEPARTMENT("2","部门");

    private String description;
    private String code;

    OrgType(String code, String description){
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
