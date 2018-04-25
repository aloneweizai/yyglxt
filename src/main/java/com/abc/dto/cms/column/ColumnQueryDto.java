package com.abc.dto.cms.column;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by relic5 on 2017/6/16.
 */
public class ColumnQueryDto extends BaseResponse{

    private ChannelInsertDto data;

    public ChannelInsertDto getData() {
        return data;
    }

    public void setData(ChannelInsertDto data) {
        this.data = data;
    }
}
