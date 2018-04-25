package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/20 18:36
 */
public class QuestionMedalBoListRs extends BaseResponse{

    private int total;

    private List<QuestionMedalBo> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionMedalBoListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionMedalBo> getDataList() {
        return dataList;
    }

    public QuestionMedalBoListRs setDataList(List<QuestionMedalBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
