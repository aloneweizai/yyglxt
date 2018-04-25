package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/8/25 16:15
 */
public class CoursewareRs extends BaseResponse{

    private Courseware data;

    public Courseware getData() {
        return data;
    }

    public CoursewareRs setData(Courseware data) {
        this.data = data;
        return this;
    }
}
