package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.SystemRecordBO;

import java.util.List;

public class  SystemRecordRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List< SystemRecordBO> dataList;
    private  SystemRecordBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< SystemRecordBO> getDataList() {
        return dataList;
    }

    public void setDataList(List< SystemRecordBO> dataList) {
        this.dataList = dataList;
    }

    public  SystemRecordBO getData() {
        return data;
    }

    public void setData( SystemRecordBO data) {
        this.data = data;
    }
}
