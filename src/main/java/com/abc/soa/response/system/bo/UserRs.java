package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuqi
 * @Date 2017/6/13 16:02
 */
public class UserRs extends BaseResponse{

    private UserBO data;

    public UserBO getData() {
        return data;
    }

    public void setData(UserBO data) {
        this.data = data;
    }
}
