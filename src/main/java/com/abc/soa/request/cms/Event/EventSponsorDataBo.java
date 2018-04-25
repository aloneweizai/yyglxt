package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/7/1.
 */
public class EventSponsorDataBo extends BaseResponse {
    private EventSponsorBo data;

    public EventSponsorBo getData() {
        return data;
    }

    public void setData(EventSponsorBo data) {
        this.data = data;
    }
}
