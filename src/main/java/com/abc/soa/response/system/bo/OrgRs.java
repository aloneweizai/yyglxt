package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.Organization;

/**
 * @Author liuqi
 * @Date 2017/6/13 17:11
 */
public class OrgRs extends BaseResponse{

    private Organization data;

    public Organization getData() {
        return data;
    }

    public void setData(Organization data) {
        this.data = data;
    }
}
