package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/6/29.
 */
public class EventRecordBo extends BaseResponse {

    private String recordId;
    private String eventId;
    private String source;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBrowsetime() {
        return browsetime;
    }

    public void setBrowsetime(String browsetime) {
        this.browsetime = browsetime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String browsetime;
    private String ip;
}
