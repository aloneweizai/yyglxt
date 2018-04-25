package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/6/30.
 */
public class EventSponsorItemBo extends BaseResponse{
    private int total;

    private List<EventSponsorBo> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<EventSponsorBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<EventSponsorBo> dataList) {
        this.dataList = dataList;
    }
}
