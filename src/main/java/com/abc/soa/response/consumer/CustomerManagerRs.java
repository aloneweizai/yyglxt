package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.CustomerManagerBo;

/**
 * @Author liuQi
 * @Date 2017/10/9 14:47
 */
public class CustomerManagerRs extends BaseResponse{

    private CustomerManagerBo data;

    public CustomerManagerBo getData() {
        return data;
    }

    public CustomerManagerRs setData(CustomerManagerBo data) {
        this.data = data;
        return this;
    }
}
