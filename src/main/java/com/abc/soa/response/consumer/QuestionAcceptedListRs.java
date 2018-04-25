package com.abc.soa.response.consumer;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.QuestionAcceptedBO;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/10 17:15
 */
public class QuestionAcceptedListRs extends BaseResponse {

    private int total;

    private List<QuestionAcceptedBO> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionAcceptedListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionAcceptedBO> getDataList() {
        return dataList;
    }

    public QuestionAcceptedListRs setDataList(List<QuestionAcceptedBO> dataList) {
        this.dataList = dataList;
        return this;
    }
}
