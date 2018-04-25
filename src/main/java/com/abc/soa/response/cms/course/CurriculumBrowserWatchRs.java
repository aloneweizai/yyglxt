package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2018/1/30 20:45
 */
public class CurriculumBrowserWatchRs extends BaseResponse {

    private CurriculumBrowserWatch data;

    public CurriculumBrowserWatch getData() {
        return data;
    }

    public CurriculumBrowserWatchRs setData(CurriculumBrowserWatch data) {
        this.data = data;
        return this;
    }
}
