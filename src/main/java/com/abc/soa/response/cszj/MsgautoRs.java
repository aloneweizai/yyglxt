package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.MsgautoBO;

import java.util.List;

public class MsgautoRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<MsgautoBO> dataList;
    private MsgautoBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<MsgautoBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<MsgautoBO> dataList) {
        this.dataList = dataList;
    }

    public MsgautoBO getData() {
        return data;
    }

    public void setData(MsgautoBO data) {
        this.data = data;
    }
}
