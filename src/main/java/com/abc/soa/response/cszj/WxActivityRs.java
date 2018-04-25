package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.WxActivityBO;

import java.util.List;

public class WxActivityRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<WxActivityBO> dataList;
    private WxActivityBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<WxActivityBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<WxActivityBO> dataList) {
        this.dataList = dataList;
    }

    public WxActivityBO getData() {
        return data;
    }

    public void setData(WxActivityBO data) {
        this.data = data;
    }
}
