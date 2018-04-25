package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/6/29.
 */
public class EventListsBo extends BaseResponse {
    private int total;

    private List<EventListBo> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<EventListBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<EventListBo> dataList) {
        this.dataList = dataList;
    }
}
