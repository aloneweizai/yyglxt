package com.abc.dto.cms.course;

/**
 * @Author liuqi
 * @Date 2017/8/24 09:53
 */
public enum TeachMethodType {

    /* 录播, 直播 ,面授 */
    recording("录播"),
    live("直播"),
    face("面授");

    private String description;

    TeachMethodType(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}
