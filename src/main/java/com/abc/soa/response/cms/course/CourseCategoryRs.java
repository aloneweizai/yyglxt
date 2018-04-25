package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuqi
 * @Date 2017/8/17 17:23
 */
public class CourseCategoryRs extends BaseResponse{

    private CourseCategory data;

    public CourseCategory getData() {
        return data;
    }

    public CourseCategoryRs setData(CourseCategory data) {
        this.data = data;
        return this;
    }
}
