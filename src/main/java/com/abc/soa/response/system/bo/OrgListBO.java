package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/5/22 15:28
 */
public class OrgListBO extends BaseResponse {

    private int total;

    private List<OrganizationBO> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrganizationBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<OrganizationBO> dataList) {
        this.dataList = dataList;
    }
}
