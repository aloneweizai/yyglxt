package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.LotteryTimeBO;

import java.util.List;

public class LotteryTimeRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<LotteryTimeBO> dataList;
    private LotteryTimeBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<LotteryTimeBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<LotteryTimeBO> dataList) {
        this.dataList = dataList;
    }

    public LotteryTimeBO getData() {
        return data;
    }

    public void setData(LotteryTimeBO data) {
        this.data = data;
    }
}
