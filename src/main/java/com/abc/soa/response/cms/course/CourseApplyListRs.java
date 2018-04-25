package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/4 11:23
 */
public class CourseApplyListRs extends BaseResponse{

    private List<CourseApply> dataList;

    private int total;

    public List<CourseApply> getDataList() {
        return dataList;
    }

    public CourseApplyListRs setDataList(List<CourseApply> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public CourseApplyListRs setTotal(int total) {
        this.total = total;
        return this;
    }
}
