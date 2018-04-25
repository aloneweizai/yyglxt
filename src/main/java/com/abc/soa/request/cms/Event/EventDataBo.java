package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/6/30.
 */
public class EventDataBo extends BaseResponse {
    private EventSaveBo data;

    public EventSaveBo getData() {
        return data;
    }

    public void setData(EventSaveBo data) {
        this.data = data;
    }
}
