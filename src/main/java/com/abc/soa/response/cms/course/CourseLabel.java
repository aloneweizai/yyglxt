package com.abc.soa.response.cms.course;

/**
 * @Author liuqi
 * @Date 2017/8/17 14:47
 */
public class CourseLabel {

    /**课程ID**varchar(64)**/
    private String curriculumId;

    /**标签ID**varchar(64)**/
    private String labelId;

    /**标签名称**varchar(30)**/
    private String labelName;

    public void setCurriculumId(String curriculumId){
        this.curriculumId = curriculumId;
    }

    public String getCurriculumId(){
        return this.curriculumId;
    }

    public void setLabelId(String labelId){
        this.labelId = labelId;
    }

    public String getLabelId(){
        return this.labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public CourseLabel setLabelName(String labelName) {
        this.labelName = labelName;
        return this;
    }
}
