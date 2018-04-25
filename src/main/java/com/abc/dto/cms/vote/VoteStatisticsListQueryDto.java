package com.abc.dto.cms.vote;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by xieyanmao on 2017/7/7.
 */
public class VoteStatisticsListQueryDto extends BaseResponse{

    private VoteStatisticsDto data;

    public VoteStatisticsDto getData() {
        return data;
    }

    public void setData(VoteStatisticsDto data) {
        this.data = data;
    }
}
