package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/6/29.
 */
public class EventApplySaveBo extends BaseResponse {

    private EventApplyBo eventApply;

    public EventApplyBo getEventApply() {
        return eventApply;
    }

    public void setEventApply(EventApplyBo eventApply) {
        this.eventApply = eventApply;
    }

    public EventApplyAttrBo getApplyAttrList() {
        return applyAttrList;
    }

    public void setApplyAttrList(EventApplyAttrBo applyAttrList) {
        this.applyAttrList = applyAttrList;
    }

    private EventApplyAttrBo applyAttrList;
}
