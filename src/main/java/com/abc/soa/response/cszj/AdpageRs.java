package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.AdpageBO;

import java.util.List;

public class AdpageRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<AdpageBO> dataList;
    private AdpageBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<AdpageBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<AdpageBO> dataList) {
        this.dataList = dataList;
    }

    public AdpageBO getData() {
        return data;
    }

    public void setData(AdpageBO data) {
        this.data = data;
    }
}
