package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.AdminLogBO;

import java.util.List;

public class AdminLogRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<AdminLogBO> dataList;
    private AdminLogBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<AdminLogBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<AdminLogBO> dataList) {
        this.dataList = dataList;
    }

    public AdminLogBO getData() {
        return data;
    }

    public void setData(AdminLogBO data) {
        this.data = data;
    }
}
