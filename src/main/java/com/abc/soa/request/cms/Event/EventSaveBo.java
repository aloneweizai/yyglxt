package com.abc.soa.request.cms.Event;


import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/6/29.
 */
public class EventSaveBo extends BaseResponse {


    private EventBo event;

    public EventBo getEvent() {
        return event;
    }

    public void setEvent(EventBo event) {
        this.event = event;
    }

    public List<EventModelItemBo> getModelItemList() {
        return modelItemList;
    }

    public void setModelItemList(List<EventModelItemBo> modelItemList) {
        this.modelItemList = modelItemList;
    }

    private List<EventModelItemBo> modelItemList;

}
