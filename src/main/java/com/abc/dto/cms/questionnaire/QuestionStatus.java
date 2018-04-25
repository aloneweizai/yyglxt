package com.abc.dto.cms.questionnaire;

/**
 * @Author liuqi
 * @Date 2017/7/27 14:31
 */
public enum QuestionStatus {

    PUBLISH("0","已发布"),
    CANCEL("1","已撤销"),
    END("2","已结束"),
    DRAFT("3","草稿");

    private String code;
    private String description;

    QuestionStatus(String code, String description){
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
