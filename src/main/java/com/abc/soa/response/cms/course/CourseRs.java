package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;

/**
 * @Author liuqi
 * @Date 2017/8/17 15:36
 */
public class CourseRs extends BaseResponse {

    private Course data;

    public Course getData() {
        return data;
    }

    public CourseRs setData(Course data) {
        this.data = data;
        return this;
    }
}
