package com.abc.soa.request.cms.course;

import com.abc.soa.request.consumer.BaseRq;

/**
 * Created by stuy on 2017/7/10.
 */
public class CourseWatchUserListCriteria extends BaseRq {
    private String curriculumId;

    private String begintime;

    private String endtime;

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
