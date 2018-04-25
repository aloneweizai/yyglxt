package com.abc.soa.request.cszj;

import com.abc.soa.request.consumer.BaseRq;

public class WxActivityRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //微信红包活动名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
