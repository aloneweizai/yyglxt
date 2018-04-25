package com.abc.dto.cms.column;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by relic5 on 2017/6/15.
 */
public class ColumnInitDto extends BaseResponse {

    private ChannelItemListDto data;

    public ChannelItemListDto getData() {
        return data;
    }

    public void setData(ChannelItemListDto data) {
        this.data = data;
    }
}
