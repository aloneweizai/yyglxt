package com.abc.soa.request.cms.Event;


import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/7/4.
 */
public class EventlltjListItemBo extends BaseResponse {
    public EventlltjListBo getData() {
        return data;
    }

    public void setData(EventlltjListBo data) {
        this.data = data;
    }

    private EventlltjListBo data;
}
