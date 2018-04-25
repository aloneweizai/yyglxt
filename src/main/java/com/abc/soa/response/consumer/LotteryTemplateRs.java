package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo. LotteryTemplateBO;

import java.util.List;

public class  LotteryTemplateRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List< LotteryTemplateBO> dataList;
    private  LotteryTemplateBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< LotteryTemplateBO> getDataList() {
        return dataList;
    }

    public void setDataList(List< LotteryTemplateBO> dataList) {
        this.dataList = dataList;
    }

    public  LotteryTemplateBO getData() {
        return data;
    }

    public void setData( LotteryTemplateBO data) {
        this.data = data;
    }
}
