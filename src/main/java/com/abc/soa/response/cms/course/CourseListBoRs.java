package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/23 17:20
 */
public class CourseListBoRs extends BaseResponse{

    private List<CourseListBo> dataList;

    private int total;

    public List<CourseListBo> getDataList() {
        return dataList;
    }

    public CourseListBoRs setDataList(List<CourseListBo> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public CourseListBoRs setTotal(int total) {
        this.total = total;
        return this;
    }
}
