package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/18 14:06
 */
public class QuestionTipOffBoListRs extends BaseResponse{

    private int total;

    private List<QuestionTipOffBo> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionTipOffBoListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionTipOffBo> getDataList() {
        return dataList;
    }

    public QuestionTipOffBoListRs setDataList(List<QuestionTipOffBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
