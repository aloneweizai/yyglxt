package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.CustomerManagerBo;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/9 15:06
 */
public class CustomerManagerListRs extends BaseResponse{

    private int total;

    private List<CustomerManagerBo> dataList;

    public int getTotal() {
        return total;
    }

    public CustomerManagerListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<CustomerManagerBo> getDataList() {
        return dataList;
    }

    public CustomerManagerListRs setDataList(List<CustomerManagerBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
