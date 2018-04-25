package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/10/23 10:28
 */
public class SensitiveWordsRs extends BaseResponse{

    private SensitiveWords data;

    public SensitiveWords getData() {
        return data;
    }

    public SensitiveWordsRs setData(SensitiveWords data) {
        this.data = data;
        return this;
    }
}
