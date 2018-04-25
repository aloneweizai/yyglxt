package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/27 23:09
 */
public class CourseCategoryTagListRs extends BaseResponse{

    private List<CourseCategoryTag> dataList;

    private int total;

    public List<CourseCategoryTag> getDataList() {
        return dataList;
    }

    public CourseCategoryTagListRs setDataList(List<CourseCategoryTag> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public CourseCategoryTagListRs setTotal(int total) {
        this.total = total;
        return this;
    }
}
