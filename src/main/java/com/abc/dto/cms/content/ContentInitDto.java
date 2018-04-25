package com.abc.dto.cms.content;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by relic5 on 2017/6/15.
 */
public class ContentInitDto extends BaseResponse{

    private ChannelItemContentListDto data;

    public ChannelItemContentListDto getData() {
        return data;
    }

    public void setData(ChannelItemContentListDto data) {
        this.data = data;
    }

}
