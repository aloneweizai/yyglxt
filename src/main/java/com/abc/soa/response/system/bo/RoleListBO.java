package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.Role;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/5/18 17:04
 */
public class RoleListBO extends BaseResponse {

    private int total;

    private List<RoleBO> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RoleBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<RoleBO> dataList) {
        this.dataList = dataList;
    }
}
