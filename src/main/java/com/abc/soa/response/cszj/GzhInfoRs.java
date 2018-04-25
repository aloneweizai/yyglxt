package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.GzhInfo;

import java.util.List;

public class GzhInfoRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<GzhInfo> dataList;
    private GzhInfo data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<GzhInfo> getDataList() {
        return dataList;
    }

    public void setDataList(List<GzhInfo> dataList) {
        this.dataList = dataList;
    }

    public GzhInfo getData() {
        return data;
    }

    public void setData(GzhInfo data) {
        this.data = data;
    }
}
