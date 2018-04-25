package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.AdpageLogBO;

import java.util.List;

public class AdpageLogRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<AdpageLogBO> dataList;
    private AdpageLogBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<AdpageLogBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<AdpageLogBO> dataList) {
        this.dataList = dataList;
    }

    public AdpageLogBO getData() {
        return data;
    }

    public void setData(AdpageLogBO data) {
        this.data = data;
    }
}
