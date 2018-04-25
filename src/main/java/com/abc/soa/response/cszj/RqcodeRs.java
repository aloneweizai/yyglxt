package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.RqcodeBO;

import java.util.List;

public class RqcodeRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<RqcodeBO> dataList;
    private RqcodeBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<RqcodeBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<RqcodeBO> dataList) {
        this.dataList = dataList;
    }

    public RqcodeBO getData() {
        return data;
    }

    public void setData(RqcodeBO data) {
        this.data = data;
    }
}
