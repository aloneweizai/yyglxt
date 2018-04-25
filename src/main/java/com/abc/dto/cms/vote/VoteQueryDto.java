package com.abc.dto.cms.vote;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by relic5 on 2017/6/30.
 */
public class VoteQueryDto extends BaseResponse{

    private VoteDto data;

    public VoteDto getData() {
        return data;
    }

    public void setData(VoteDto data) {
        this.data = data;
    }
}
