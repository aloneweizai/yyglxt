package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

public class MobileModleMsgRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
