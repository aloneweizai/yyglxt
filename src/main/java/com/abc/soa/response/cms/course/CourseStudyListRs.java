package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/30 16:56
 */
public class CourseStudyListRs extends BaseResponse {

    private List<CourseStudy> dataList;

    private int total;

    public List<CourseStudy> getDataList() {
        return dataList;
    }

    public CourseStudyListRs setDataList(List<CourseStudy> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public CourseStudyListRs setTotal(int total) {
        this.total = total;
        return this;
    }
}
