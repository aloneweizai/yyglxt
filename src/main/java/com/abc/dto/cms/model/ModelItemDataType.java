package com.abc.dto.cms.model;

/**
 * 系统模型管理 数据类型
 * @Author liuqi
 * @Date 2017/5/17 13:33
 */
public enum ModelItemDataType {

    STRING_TEXT("1","字符串文本"),
    INT_TEXT("2","整型文本"),
    FLOAT_TEXT("3","浮点型文本"),
    TEXTAREA("4","文本区"),
    DATE("5","日期"),
    SELECT("6","下拉列表"),
    CHECKBOX("7","复选框"),
    RADIO("8","单选框"),
    FILE("9","附件"),
    IMAGE("10","图片"),
    COLOR_PICKER("11","颜色选择器"),
    MEDIA("12","多媒体"),
    ATTACHEMENT("13","多附件");

    private String description;
    private String code;

    ModelItemDataType(String code, String description) {
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
