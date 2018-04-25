package com.abc.dto.cms.topic;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by relic5 on 2017/6/17.
 */
public class TopicQueryWrapper extends BaseResponse{

    private TopicDto data;

    public TopicDto getData() {
        return data;
    }

    public void setData(TopicDto data) {
        this.data = data;
    }
}
