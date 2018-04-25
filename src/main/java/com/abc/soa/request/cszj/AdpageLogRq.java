package com.abc.soa.request.cszj;

import com.abc.soa.request.consumer.BaseRq;

public class AdpageLogRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String adpageid;

    @Override
    public String toString() {
        return "AdpageLogRq{" +
                "adpageid='" + adpageid + '\'' +
                '}';
    }

    public String getAdpageid() {
        return adpageid;
    }

    public void setAdpageid(String adpageid) {
        this.adpageid = adpageid;
    }
}
