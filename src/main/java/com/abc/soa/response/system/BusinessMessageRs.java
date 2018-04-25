package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.BusinessMessageBO;

import java.util.List;

public class BusinessMessageRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<BusinessMessageBO> dataList;
    private  BusinessMessageBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< BusinessMessageBO> getDataList() {
        return dataList;
    }

    public void setDataList(List< BusinessMessageBO> dataList) {
        this.dataList = dataList;
    }

    public  BusinessMessageBO getData() {
        return data;
    }

    public void setData( BusinessMessageBO data) {
        this.data = data;
    }
}
