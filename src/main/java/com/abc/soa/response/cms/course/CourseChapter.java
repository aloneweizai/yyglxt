package com.abc.soa.response.cms.course;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/21 14:35
 */
public class CourseChapter {

    /**章节ID**varchar(64)**/
    private String chapterId;

    /**课程ID**varchar(64)**/
    private String curriculumId;

    /**章节名称**varchar(100)**/
    private String chapterName;

    /**章节顺序**tinyint(4)**/
    private Integer chapterSeq;

    /**课件**/
    private List<Courseware> coursewareList;

    public void setChapterId(String chapterId){
        this.chapterId = chapterId;
    }

    public String getChapterId(){
        return this.chapterId;
    }

    public void setCurriculumId(String curriculumId){
        this.curriculumId = curriculumId;
    }

    public String getCurriculumId(){
        return this.curriculumId;
    }

    public void setChapterName(String chapterName){
        this.chapterName = chapterName;
    }

    public String getChapterName(){
        return this.chapterName;
    }

    public void setChapterSeq(Integer chapterSeq){
        this.chapterSeq = chapterSeq;
    }

    public Integer getChapterSeq(){
        return this.chapterSeq;
    }

    public List<Courseware> getCoursewareList() {
        return coursewareList;
    }

    public CourseChapter setCoursewareList(List<Courseware> coursewareList) {
        this.coursewareList = coursewareList;
        return this;
    }
}
