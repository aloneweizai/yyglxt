package com.abc.soa.response.cms.course;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/17 17:22
 * 课程分类
 */
public class CourseCategory {

    /**分类ID**varchar(64)**/
    private String classifyId;

    /**父分类ID**varchar(64)**/
    private String parentId;

    /**分类名称**varchar(100)**/
    private String classifyName;

    /*父类名称*/
    private String parentName;

    /**排列顺序**int**/
    private Integer priority;

    /**是否显示**int**/
    private Integer isDisplay;

    private List<CourseCategoryTag> tagList;

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public CourseCategory setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
        return this;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public CourseCategory setClassifyId(String classifyId) {
        this.classifyId = classifyId;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public CourseCategory setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public CourseCategory setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public CourseCategory setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public String getParentName() {
        return parentName;
    }

    public CourseCategory setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public List<CourseCategoryTag> getTagList() {
        return tagList;
    }

    public CourseCategory setTagList(List<CourseCategoryTag> tagList) {
        this.tagList = tagList;
        return this;
    }
}
