package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2017/11/23.
 */
public class AddressListRes extends BaseResponse{
    private List<AddressBo> dataList;
    private int total;

    public List<AddressBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<AddressBo> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
