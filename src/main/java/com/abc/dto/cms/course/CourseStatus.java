package com.abc.dto.cms.course;

import com.abc.soa.response.cms.course.Course;

/**
 * @Author liuQi
 * @Date 2017/8/24 09:38
 * 课程状态
 */
public enum CourseStatus {

    /* 未发布，已发布，已撤销 */
    DRAFT("0","未发布"),
    PUBLISH("1","已发布"),
    CANCEL("2","已撤销");

    private String code;
    private String description;

    CourseStatus(String code, String description){
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
