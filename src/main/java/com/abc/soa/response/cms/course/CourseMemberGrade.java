package com.abc.soa.response.cms.course;

/**
 * @Author liuQi
 * @Date 2017/8/17 15:13
 * 课程等级关联
 */
public class CourseMemberGrade {

    /**课程ID**varchar(64)**/
    private String curriculumId;

    /**会员等级**varchar(64)**/
    private String memberGrade;



    public void setCurriculumId(String curriculumId){
        this.curriculumId = curriculumId;
    }

    public String getCurriculumId(){
        return this.curriculumId;
    }

    public void setMemberGrade(String memberGrade){
        this.memberGrade = memberGrade;
    }

    public String getMemberGrade(){
        return this.memberGrade;
    }

}
