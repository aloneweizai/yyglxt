package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/16.
 */
public class CurriculumListDetailsBo extends BaseResponse {
    public CurriculumsyBo getData() {
        return data;
    }

    public void setData(CurriculumsyBo data) {
        this.data = data;
    }

    private CurriculumsyBo data;

}
