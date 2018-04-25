package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/16 17:46
 */
public class CoursewareListRs extends BaseResponse{

    private List<Courseware> dataList;

    private int total;

    public List<Courseware> getDataList() {
        return dataList;
    }

    public CoursewareListRs setDataList(List<Courseware> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public CoursewareListRs setTotal(int total) {
        this.total = total;
        return this;
    }
}
