package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.LotteryActivityipBO;

import java.util.List;

public class LotteryActivityipRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List< LotteryActivityipBO> dataList;
    private  LotteryActivityipBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< LotteryActivityipBO> getDataList() {
        return dataList;
    }

    public void setDataList(List< LotteryActivityipBO> dataList) {
        this.dataList = dataList;
    }

    public  LotteryActivityipBO getData() {
        return data;
    }

    public void setData( LotteryActivityipBO data) {
        this.data = data;
    }
}
