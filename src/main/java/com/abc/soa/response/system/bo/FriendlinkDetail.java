package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.Friendlink;

/**
 * Created by Administrator on 2017-06-20.
 */
public class FriendlinkDetail extends BaseResponse {

    private Friendlink data;

    public Friendlink getData() {
        return data;
    }

    public void setData(Friendlink data) {
        this.data = data;
    }
}
