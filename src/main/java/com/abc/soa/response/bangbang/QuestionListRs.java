package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/11 15:30
 */
public class QuestionListRs extends BaseResponse{

    private int total;

    private List<Question> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<Question> getDataList() {
        return dataList;
    }

    public QuestionListRs setDataList(List<Question> dataList) {
        this.dataList = dataList;
        return this;
    }
}
