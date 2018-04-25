package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/23.
 */
public class KjBo extends BaseResponse {
    private CurriculumCoursewareBo data;

    public CurriculumCoursewareBo getData() {
        return data;
    }

    public void setData(CurriculumCoursewareBo data) {
        this.data = data;
    }
}
