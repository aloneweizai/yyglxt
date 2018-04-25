package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/6/29.
 */
public class EventApplyItemBo extends BaseResponse{
    private List<EventApplyBo> dataList;

    public List<EventApplyBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<EventApplyBo> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;
}
