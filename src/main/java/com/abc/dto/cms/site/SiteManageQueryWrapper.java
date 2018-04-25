package com.abc.dto.cms.site;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by relic5 on 2017/6/17.
 */
public class SiteManageQueryWrapper extends BaseResponse{

    private SiteManageDto data;

    public SiteManageDto getData() {
        return data;
    }

    public void setData(SiteManageDto data) {
        this.data = data;
    }
}
