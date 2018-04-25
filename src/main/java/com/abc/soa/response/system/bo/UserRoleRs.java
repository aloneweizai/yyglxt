package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.UserRole;

/**
 * @Author liuqi
 * @Date 2017/7/19 18:30
 */
public class UserRoleRs extends BaseResponse {

    private UserRole data;

    public UserRole getData() {
        return data;
    }

    public void setData(UserRole data) {
        this.data = data;
    }
}
