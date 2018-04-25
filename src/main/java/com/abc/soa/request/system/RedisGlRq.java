package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

public class RedisGlRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
