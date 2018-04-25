package com.abc.dto.cms.questionnaire;

/**
 * @Author liuqi
 * @Date 2017/6/27 16:23
 */
public enum SubjectType {

    RADIO("1","单选题"),
    CHECKBOX("2","多选题"),
    TEXT("3","单行文本题"),
    TEXTAREA("4","多行文本题"),
    TEXT_MULTIPLE("5","填空题");

    private String description;
    private String code;

    SubjectType(String code, String description){
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
