package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.WxuserBO;

import java.util.List;

public class WxuserRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<WxuserBO> dataList;
    private WxuserBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<WxuserBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<WxuserBO> dataList) {
        this.dataList = dataList;
    }

    public WxuserBO getData() {
        return data;
    }

    public void setData(WxuserBO data) {
        this.data = data;
    }
}
