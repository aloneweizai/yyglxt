package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/4 15:45
 */
public class CourseSituationRs extends BaseResponse{

    private CourseSituation data;

    public CourseSituation getData() {
        return data;
    }

    public CourseSituationRs setData(CourseSituation data) {
        this.data = data;
        return this;
    }
}
