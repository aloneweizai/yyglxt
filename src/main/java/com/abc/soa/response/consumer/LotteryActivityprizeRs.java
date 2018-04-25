package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo. LotteryActivityprizeBO;

import java.util.List;

public class  LotteryActivityprizeRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List< LotteryActivityprizeBO> dataList;
    private  LotteryActivityprizeBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< LotteryActivityprizeBO> getDataList() {
        return dataList;
    }

    public void setDataList(List< LotteryActivityprizeBO> dataList) {
        this.dataList = dataList;
    }

    public  LotteryActivityprizeBO getData() {
        return data;
    }

    public void setData( LotteryActivityprizeBO data) {
        this.data = data;
    }
}
