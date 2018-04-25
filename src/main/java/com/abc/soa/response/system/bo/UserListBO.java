package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/5/17 8:53
 */
public class UserListBO extends BaseResponse {

    private int total;

    private List<UserBO> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UserBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserBO> dataList) {
        this.dataList = dataList;
    }
}
