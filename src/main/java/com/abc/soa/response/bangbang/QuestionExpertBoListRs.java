package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/19 16:48
 */
public class QuestionExpertBoListRs extends BaseResponse{

    private int total;

    private List<QuestionExpertBo> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionExpertBoListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionExpertBo> getDataList() {
        return dataList;
    }

    public QuestionExpertBoListRs setDataList(List<QuestionExpertBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
