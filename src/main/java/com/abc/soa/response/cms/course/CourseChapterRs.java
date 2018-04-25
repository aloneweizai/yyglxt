package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/8/21 14:37
 */
public class CourseChapterRs extends BaseResponse{

    private CourseChapter data;

    public CourseChapter getData() {
        return data;
    }

    public CourseChapterRs setData(CourseChapter data) {
        this.data = data;
        return this;
    }
}
