package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/11/20 14:43
 */
public class CheatsRs extends BaseResponse{

    private Cheats data;

    public Cheats getData() {
        return data;
    }

    public CheatsRs setData(Cheats data) {
        this.data = data;
        return this;
    }
}
