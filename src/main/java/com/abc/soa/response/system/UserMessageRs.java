package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.bo.UserMessageBO;

import java.util.List;

public class UserMessageRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<UserMessageBO> dataList;
    private  UserMessageBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< UserMessageBO> getDataList() {
        return dataList;
    }

    public void setDataList(List< UserMessageBO> dataList) {
        this.dataList = dataList;
    }

    public  UserMessageBO getData() {
        return data;
    }

    public void setData( UserMessageBO data) {
        this.data = data;
    }
}
