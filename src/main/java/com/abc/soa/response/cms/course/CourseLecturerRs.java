package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/8/16 09:52
 */
public class CourseLecturerRs extends BaseResponse{

    private CourseLecturer data;

    public CourseLecturer getData() {
        return data;
    }

    public CourseLecturerRs setData(CourseLecturer data) {
        this.data = data;
        return this;
    }
}
