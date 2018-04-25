package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo. LotteryBO;

import java.util.List;

public class  LotteryRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List< LotteryBO> dataList;
    private  LotteryBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< LotteryBO> getDataList() {
        return dataList;
    }

    public void setDataList(List< LotteryBO> dataList) {
        this.dataList = dataList;
    }

    public  LotteryBO getData() {
        return data;
    }

    public void setData( LotteryBO data) {
        this.data = data;
    }
}
