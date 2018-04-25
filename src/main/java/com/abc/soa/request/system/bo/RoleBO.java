package com.abc.soa.request.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.Role;

/**
 * @Author liuqi
 * @Date 2017/5/19 9:17
 */
public class RoleBO extends BaseResponse{

    private Role role;

    private String menuIds;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
