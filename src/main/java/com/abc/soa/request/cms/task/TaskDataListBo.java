package com.abc.soa.request.cms.task;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/7/5.
 */
public class TaskDataListBo extends BaseResponse {
    private List<TaskBo>  dataList;

    public int getTotal() {
        return total;
    }

    public List<TaskBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<TaskBo> dataList) {
        this.dataList = dataList;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;

}
