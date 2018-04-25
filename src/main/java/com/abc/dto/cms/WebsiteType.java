package com.abc.dto.cms;

/**
 * @Author liuqi
 * @Date 2017/8/12 16:10
 */
public enum WebsiteType {

    TAX("TAX","财税网"),
    TAX_EXPERT("TAX_EXPERT","财税专家");

    private String code;
    private String description;

    WebsiteType(String code, String description) {
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
