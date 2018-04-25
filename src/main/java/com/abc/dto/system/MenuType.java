package com.abc.dto.system;

/**
 * 菜单类型
 * @Author liuqi
 * @Date 2017/5/17 13:33
 */
public enum MenuType {

    CONTENT("1","目录"),
    MENU("2","菜单"),
    BUTTON("3","按钮");

    private String description;
    private String code;

    MenuType(String code,String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public String getCode() {
        return code;
    }
}
