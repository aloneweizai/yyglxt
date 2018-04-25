package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/16 11:17
 */
public class QuestionHeadmanListRs extends BaseResponse{

    private int total;

    private List<QuestionHeadman> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionHeadmanListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionHeadman> getDataList() {
        return dataList;
    }

    public QuestionHeadmanListRs setDataList(List<QuestionHeadman> dataList) {
        this.dataList = dataList;
        return this;
    }
}
