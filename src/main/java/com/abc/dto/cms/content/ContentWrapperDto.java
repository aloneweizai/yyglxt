package com.abc.dto.cms.content;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by relic5 on 2017/6/17.
 */
public class ContentWrapperDto extends BaseResponse{

    private ContentInsertOrUpdateDto data;

    public ContentInsertOrUpdateDto getData() {
        return data;
    }

    public void setData(ContentInsertOrUpdateDto data) {
        this.data = data;
    }
}
