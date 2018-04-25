package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/16 09:51
 */
public class CourseLecturerListRs extends BaseResponse {

    private List<CourseLecturer> dataList;

    private int total;

    public List<CourseLecturer> getDataList() {
        return dataList;
    }

    public CourseLecturerListRs setDataList(List<CourseLecturer> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public CourseLecturerListRs setTotal(int total) {
        this.total = total;
        return this;
    }
}
