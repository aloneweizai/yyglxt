package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/17 17:24
 */
public class CourseCategoryListRs extends BaseResponse{

    private List<CourseCategory> dataList;

    public List<CourseCategory> getDataList() {
        return dataList;
    }

    public CourseCategoryListRs setDataList(List<CourseCategory> dataList) {
        this.dataList = dataList;
        return this;
    }
}
