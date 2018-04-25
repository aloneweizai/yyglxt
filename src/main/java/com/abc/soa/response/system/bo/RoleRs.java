package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuqi
 * @Date 2017/6/13 17:47
 */
public class RoleRs extends BaseResponse{

    private RoleBO data;

    public RoleBO getData() {
        return data;
    }

    public void setData(RoleBO data) {
        this.data = data;
    }
}
