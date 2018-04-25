package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.RepackBO;

import java.util.List;

public class RepackRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<RepackBO> dataList;
    private RepackBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RepackBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<RepackBO> dataList) {
        this.dataList = dataList;
    }

    public RepackBO getData() {
        return data;
    }

    public void setData(RepackBO data) {
        this.data = data;
    }
}
